package org.khanhdunk.web_dat_ve_xem_phim.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Role;


import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {

   /* private Long userId ;*/

    private String ma ;//KH001

    @Size(min = 3 , message = "Username dưới 3 ký tự ")
    private String userName ;

    @Email
    private String email ;
    private String fullName ;

    @Size(min = 8 , message = "Mật khẩu tối thiểu 8 ký tự  ")
    private String password ;

    @Size(min = 10 , message = "Số điện thọai không dưới 10 số ")
    private String phone ;

    private Integer point ;
    @Column(name = "gender", length = 50)
    private String gender ;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDay;

    private Set<Role> role;





}
