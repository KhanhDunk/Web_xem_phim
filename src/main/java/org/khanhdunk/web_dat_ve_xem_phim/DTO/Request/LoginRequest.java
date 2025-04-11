package org.khanhdunk.web_dat_ve_xem_phim.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String Email ;
    private String Password ;
    
}
