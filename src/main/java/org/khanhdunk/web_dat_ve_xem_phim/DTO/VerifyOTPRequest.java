package org.khanhdunk.web_dat_ve_xem_phim.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VerifyOTPRequest {
    @NotBlank(message = "Email không được để trống")
    private String email;

    @NotBlank(message = "Mã OTP không được để trống")
    private String otp;

}
