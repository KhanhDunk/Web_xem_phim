package org.khanhdunk.web_dat_ve_xem_phim.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  userId  ;

    private String userName ;
    private String fullName ;
    private String email ;
    private String password ;
    private Integer phone ;
    private Date birthDay ;
    private Date createAt ;
    private String avatar ;
    private Integer point ;
    private String gender ;

    @OneToOne
    @JoinColumn(name="role_id")
    private Role Role ;
}
