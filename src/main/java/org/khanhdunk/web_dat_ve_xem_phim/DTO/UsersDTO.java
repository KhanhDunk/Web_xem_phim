package org.khanhdunk.web_dat_ve_xem_phim.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Role_;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {

    private Long userId ;

    private String ma ;//KH001
    private String userName ;
    private String email ;
    private String fullName ;
    private String password ;
    private String phone ;
    private Integer point ;
    private String gender ;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDay;


    private Role_ role;



}
