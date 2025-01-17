package org.khanhdunk.web_dat_ve_xem_phim.Controller;

import org.khanhdunk.web_dat_ve_xem_phim.DTO.LoginRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.LoginResponse;
import org.khanhdunk.web_dat_ve_xem_phim.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class UserController {

    @Autowired
    private UsersService userService ;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@RequestBody LoginRequest loginRequest)
    {
        LoginResponse response = userService.login(loginRequest);
        if (response.getMessage().equals("Login successful")) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }
}
