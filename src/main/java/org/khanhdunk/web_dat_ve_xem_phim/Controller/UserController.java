package org.khanhdunk.web_dat_ve_xem_phim.Controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.*;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.RegisterRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.ResetPasswordRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.UserUpdateRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.RegisterResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.ResetPasswordResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.UserResponse;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Users;
import org.khanhdunk.web_dat_ve_xem_phim.Repository.UsersRepository;
import org.khanhdunk.web_dat_ve_xem_phim.Service.UserService;
import org.khanhdunk.web_dat_ve_xem_phim.Service.iplm.MailService;
import org.khanhdunk.web_dat_ve_xem_phim.Service.iplm.UserServiceIpm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
/*import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;*/
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user/account")
public class UserController {


    @Autowired
    private MailService mailService;

    @Autowired
    private UsersRepository usersRepository ;
    @Autowired
    private UserService userService ;


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
    private ResponseDTO<List<UserResponse>> getUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username : {} ", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        List<UserResponse> userList = userService.getUsers();
        return ResponseDTO.<List<UserResponse>>builder()
                .message("Lấy thành công")
                .status(HttpStatus.OK)
                .data(userList)
                .build();
    }

    @GetMapping("/get/{userId}")
    private ResponseDTO<Users> getUser(@PathVariable Long userId)
    {

       Users getUserId = userService.getUserId(userId);
        return ResponseDTO.<Users>builder()
                .message("Lấy id người dùng thành công")
                .status(HttpStatus.OK)
                .data(getUserId)
                .build();

    }


    @GetMapping("/{myInfo}") // Lấy thông tin của chính user đó
    private ResponseDTO<UsersDTO> getMyInfo()
    {


        return ResponseDTO.<UsersDTO>builder()
                .message("Lấy id người dùng thành công")
                .status(HttpStatus.OK)
                .data(userService.getMyInfo())
                .build();

    }

    @PutMapping("/update/{userId}")
     private  ResponseDTO<UserResponse> update(@RequestBody UserUpdateRequest request, @PathVariable Long userId)
    {


        return ResponseDTO.<UserResponse>builder()
                .message("Cập nhật người dùng thành công")
                .status(HttpStatus.OK)
                .data(userService.update(request , userId))
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


    @PostMapping("/register")
    ResponseDTO<RegisterResponse> register(@RequestBody RegisterRequest request )
    {
        var result = userService.register(request);
        return ResponseDTO.<RegisterResponse>builder()
                .data(result)
                .build();
    }


    @PutMapping("/reset-password")
    public ResponseDTO<ResetPasswordResponse> resetPassword(@RequestBody ResetPasswordRequest request)
    {
       var result =  userService.resetPassword(request);
        return ResponseDTO.<ResetPasswordResponse>builder()
                .data(result)
                .build();
    }
}
