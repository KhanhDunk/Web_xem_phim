package org.khanhdunk.web_dat_ve_xem_phim.Service.iplm;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.RegisterRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.ResetPasswordRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.UserUpdateRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.RegisterResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.ResetPasswordResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.RoleResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.UserResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.UsersDTO;

import org.khanhdunk.web_dat_ve_xem_phim.Entity.Role;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Users;
import org.khanhdunk.web_dat_ve_xem_phim.Mapper.UserMapper;
import org.khanhdunk.web_dat_ve_xem_phim.Repository.RoleRepository;
import org.khanhdunk.web_dat_ve_xem_phim.Repository.UsersRepository;
import org.khanhdunk.web_dat_ve_xem_phim.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserServiceIpm implements UserService {

    @Autowired
    private UsersRepository userRepo ;
    @Autowired
    private UserMapper userMapper ;

    @Autowired
    private RoleRepository roleRepo ;

    @Autowired
    private PasswordEncoder passwordEncoder ;

    @PreAuthorize("hasRole('ADMIN')")
    public Users create(UsersDTO dto) {


        if(userRepo.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }

        Users user = userMapper.toUser(dto);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        user.setPassword(passwordEncoder.encode(dto.getPassword()));




       return  userRepo.save(user) ;



    }

    @PreAuthorize("hasRole('ADMIN')")
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

    @Override
    public Set<RoleResponse> mapRolesToRoleResponses(Set<Role> roles) {
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

    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long userId) {
        userRepo.deleteById(userId);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')") //Có role admin mới truy cập được
    //PreAuthorize kiểm tra phân quyền trước khi thực hiện logic bên trong

    public List<UserResponse> getUsers() {
        log.info("In method get Users");
        return userRepo.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    //hasRole dùng tiền tố Role ở trước
    //hasAuthority không cần tiền tố

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


    public RegisterResponse register(RegisterRequest request )
    {
        if(userRepo.existsByEmail(request.getEmail()))
        {
            throw new RuntimeException("Email đã tồn tại");
        }

        Users users =   userMapper.toUserEmail(request);
        users.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepo.save(users);

        return userMapper.responseRegister(users);
    }

    public ResetPasswordResponse resetPassword (ResetPasswordRequest request)
    {
        Users user = userRepo.findByUserName(request.getUserName()).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng ")) ;

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);

         userRepo.save(user);

        return new ResetPasswordResponse("Đặt lại mật khẩu thành công");

    }



}
