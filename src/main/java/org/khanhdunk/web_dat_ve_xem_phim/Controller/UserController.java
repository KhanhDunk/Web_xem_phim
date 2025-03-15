package org.khanhdunk.web_dat_ve_xem_phim.Controller;

import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.*;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Users;
import org.khanhdunk.web_dat_ve_xem_phim.Repository.UsersRepository;
import org.khanhdunk.web_dat_ve_xem_phim.Service.UsersService;
import org.khanhdunk.web_dat_ve_xem_phim.Service.iplm.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user/api/account")
public class UserController {


    @Autowired
    private MailService mailService;

    @Autowired
    private  PasswordEncoder encoder;

    @Autowired
    private UsersRepository usersRepository ;

    @Autowired
    private UsersService userService ;

    @GetMapping
    public String userAccess() {
        return "Chào User! Bạn có quyền truy cập.";
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam Long id) {
        try {
            UsersDTO user = userService.getById(id);
            if (user != null) {
                return ResponseEntity.ok(user); // ✅ Trả về đối tượng UsersDTO thay vì chuỗi
            }
            return ResponseEntity.status(404).body("Không tìm thấy người dùng với ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi hệ thống: " + e.getMessage());
        }
    }



    @PutMapping("/update")
    public ResponseEntity<String> update(UsersDTO dto)
    {
        try {
            userService.Update(dto);
            return ResponseEntity.ok("Cập nhật thành công");
        }
        catch (RuntimeException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) {
        try {
            userService.login(email, password);
            return ResponseEntity.ok("Đăng nhập thành công");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    // "Cơ sở dữ liệu" giả lập
    private Map<String, String> otpDatabase = new HashMap<>();


    // Đăng ký tài khoản
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UsersDTO request) {
        ResponseEntity<String> response = userService.register(request);

        if (response.getBody().equals("Email đã được đăng ký")) {
            return ResponseEntity.badRequest().body("Không thành công");
        }
        return response;
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOTP(@Valid @RequestBody VerifyOTPRequest request) {
        String email = request.getEmail();

       Optional<Users> user = usersRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.badRequest().body("Người dùng không tồn tại.");
        }

        // Xác minh thành công (không cần lưu trạng thái)
        return ResponseEntity.ok("Xác nhận tài khoản thành công!");
    }



}
