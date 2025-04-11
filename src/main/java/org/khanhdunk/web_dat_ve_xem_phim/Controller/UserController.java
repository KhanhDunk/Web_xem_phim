package org.khanhdunk.web_dat_ve_xem_phim.Controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.*;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Users;
import org.khanhdunk.web_dat_ve_xem_phim.Repository.UsersRepository;
import org.khanhdunk.web_dat_ve_xem_phim.Service.UsersService;
import org.khanhdunk.web_dat_ve_xem_phim.Service.iplm.MailService;
import org.khanhdunk.web_dat_ve_xem_phim.Service.iplm.UserServiceIpm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/*import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;*/
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/user/account")
public class UserController {


    @Autowired
    private MailService mailService;



    @Autowired
    private UsersRepository usersRepository ;
    @Autowired
    private UserServiceIpm userService ;


    @PostMapping("/create")
    private ResponseDTO<Users> createUser(@RequestBody @Valid UsersDTO dto)
    {
       userService.create(dto);
       return  ResponseDTO.<Users>builder()
               .message("Tạo thành công ")
               .status(HttpStatus.OK)

               .build();
    }

    @GetMapping("/get")
     private ResponseDTO<List<Users>> getUser(){
        var authentication = SecurityContextHolder.getContext().getAuthentication() ;

        log.info("Username : {} " , authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

     List<Users> userList = userService.getUser();
       return  ResponseDTO.<List<Users>>builder()
               .message("Lấy thành công")
               .status(HttpStatus.OK)
               .data(userList)
               .build();
    }

    @GetMapping("/get/{userId}")
    private ResponseDTO<Users> getUsser(@PathVariable Long userId)
    {

       Users getUserId = userService.getUserId(userId);
        return ResponseDTO.<Users>builder()
                .message("Lấy id người dùng thành công")
                .status(HttpStatus.OK)
                .data(getUserId)
                .build();

    }

    @PutMapping("/update/{userId}")
     private  ResponseDTO<Users> update(@RequestBody UsersDTO dto, @PathVariable Long userId)
    {

       Users user= userService.update(dto , userId) ;
        return ResponseDTO.<Users>builder()
                .message("Cập nhật người dùng thành công")
                .status(HttpStatus.OK)
                .data(user)
                .build();
    }

    @DeleteMapping("/delete/{userId}")
    private ResponseDTO<Void> delete(@PathVariable Long userId)
    {

        userService.delete(userId);

        return ResponseDTO.<Void>builder()

                .message("Xoá thành công")
                .status(HttpStatus.OK)
                .build();
    }
}
