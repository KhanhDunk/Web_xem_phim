package org.khanhdunk.web_dat_ve_xem_phim.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/public")
public class PublicController {




        @GetMapping
        public String publicAccess() {
            return "Trang này ai cũng có thể xem.";
        }
    }


