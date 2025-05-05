package org.khanhdunk.web_dat_ve_xem_phim.DTO.Response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {

    @NotBlank(message= "Không được để trống")
    @Size(min = 3 , max = 50 , message = "Tên đăng nhập không được dưới 3 và trên 50 ký tự ")
    private String UserName ;
    private String Password ;


    private String email ;
    private String fullName ;
    private String phone ;
    private String gender ;
}
