package org.khanhdunk.web_dat_ve_xem_phim.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {

    private Long userId ;
    private String userName ;
    private String email ;
    private String fullName ;
    private String password ;
    private Integer phone ;
    private Integer point ;
    private String gender ;

}
