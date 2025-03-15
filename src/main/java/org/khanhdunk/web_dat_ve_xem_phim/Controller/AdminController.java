package org.khanhdunk.web_dat_ve_xem_phim.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Chào Admin! Bạn có quyền truy cập.";
    }
}
