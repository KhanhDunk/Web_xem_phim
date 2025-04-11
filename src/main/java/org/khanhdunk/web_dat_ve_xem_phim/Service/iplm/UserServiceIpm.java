package org.khanhdunk.web_dat_ve_xem_phim.Service.iplm;

import org.khanhdunk.web_dat_ve_xem_phim.DTO.UsersDTO;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Role_;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Users;
import org.khanhdunk.web_dat_ve_xem_phim.Mapper.UserMapper;
import org.khanhdunk.web_dat_ve_xem_phim.Repository.UsersRepository;
import org.khanhdunk.web_dat_ve_xem_phim.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceIpm implements UsersService {

    @Autowired
    private UsersRepository userRepo ;
    @Autowired
    private UserMapper userMapper ;
    @Override
    public Users create(UsersDTO dto) {


        if(userRepo.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }

        Users user = userMapper.toUser(dto);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        HashSet<String> role = new HashSet<>(); // tạo mặc định role user
         role.add(Role_.USER.name());

        user.setRole(Role_.USER);


       return  userRepo.save(user) ;



    }

    @Override
    public Users update(UsersDTO dto, Long userId) {


            Users user= getUserId(userId);

            userMapper.updateUserFromDTO(dto,user);


     return userRepo.save(user);
    }

    @Override
    public void delete(Long userId) {
        userRepo.deleteById(userId);
    }

    @Override
    public List<Users> getUser() {
        return userRepo.findAll();
    }

    @Override
    public Users getUserId(Long id)
    {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng")) ;
    }
}
