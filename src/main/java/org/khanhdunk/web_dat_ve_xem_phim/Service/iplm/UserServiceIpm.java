package org.khanhdunk.web_dat_ve_xem_phim.Service.iplm;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.UserUpdateRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.RoleResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.UserResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.UsersDTO;

import org.khanhdunk.web_dat_ve_xem_phim.Entity.Role;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Users;
import org.khanhdunk.web_dat_ve_xem_phim.Mapper.UserMapper;
import org.khanhdunk.web_dat_ve_xem_phim.Repository.RoleRepository;
import org.khanhdunk.web_dat_ve_xem_phim.Repository.UsersRepository;
import org.khanhdunk.web_dat_ve_xem_phim.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserServiceIpm implements UsersService {

    @Autowired
    private UsersRepository userRepo ;
    @Autowired
    private UserMapper userMapper ;

    @Autowired
    private RoleRepository roleRepo ;

    @Autowired
    private PasswordEncoder passwordEncoder ;
    @Override
    public Users create(UsersDTO dto) {


        if(userRepo.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }

        Users user = userMapper.toUser(dto);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        user.setPassword(passwordEncoder.encode(dto.getPassword()));




       return  userRepo.save(user) ;



    }

    public UserResponse update(UserUpdateRequest request, Long userId) {


        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Log user trước khi cập nhật

        userMapper.updateUser(request,user);

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        var roles =   roleRepo.findAllById(request.getRole());
        user.setRole(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepo.save(user));
    }

    private Set<RoleResponse> mapRolesToRoleResponses(Set<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return null;
        }

        Set<RoleResponse> roleResponses = new HashSet<>();
        for (Role role : roles) {
            RoleResponse roleResponse = RoleResponse.builder()
                    .name(role.getName())

                    .description(role.getDescription())

                    // Các trường khác của RoleResponse nếu có
                    .build();
            roleResponses.add(roleResponse);
        }

        return roleResponses;
    }

    @Override
    public void delete(Long userId) {
        userRepo.deleteById(userId);
    }


    @PreAuthorize("hasAnyRole('ADMIN','STAFF')") //Có role admin mới truy cập được
    //PreAuthorize kiểm tra phân quyền trước khi thực hiện logic bên trong
    @Override
    public List<Users> getUser() {
        log.info("In method get Users");
        return userRepo.findAll();
    }

  /*  @PostAuthorize("returnObject.userName == authentication.name") // username == name thì trả về không thì thoy
    //Chỉ cho phép user lấy thông tin của chính mình thoy
    //PostAuthorize kiểm tra phân quyền sau khi thực hiện method bên trong
    @Override
    public Users getUserId(Long id)
    {

        log.info("In method get user by id ");
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng")) ;
    }*/


    @PostAuthorize("returnObject.userName == authentication.name")
    @Override
    public Users getUserId(Long id) {
        log.info("In method get user by id");
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
    }


    public UsersDTO getMyInfo()
    {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();


        Users user =  userRepo.findByUserName(name).orElseThrow(
                () -> new RuntimeException("Không tìm thấy người dùng"));


        return userMapper.toUserDTO(user);

    }
}
