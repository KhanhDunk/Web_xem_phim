/*
package org.khanhdunk.web_dat_ve_xem_phim.Controller;

import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.LoginRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.UsersDTO;
import org.khanhdunk.web_dat_ve_xem_phim.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsersServiceIplm userService ;
  */
/*  private final AuthenticationManager authenticationManager;
   private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }*//*


    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

  @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsersDTO request) {
        // Kiểm tra xem username đã tồn tại chưa
      return userService.register(request);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
      try {
          userService.login(request.getEmail(), request.getPassword());
          return ResponseEntity.ok("Đăng nhập thành công");
      }
      catch (RuntimeException e)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }


}
*/
