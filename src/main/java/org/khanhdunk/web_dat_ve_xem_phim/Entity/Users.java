package org.khanhdunk.web_dat_ve_xem_phim.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  userId  ;

    private String ma ;//KH001
    private String userName ;
    private String fullName ;
    private String email ;
    private String password ;
    private String phone ;
    private LocalDate birthDay ;
    private LocalDate createAt ;
    private String avatar ;
    private Integer point ;
    private String gender ;

    @ManyToMany
    private Set<Role>  role ;

   /* @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role_ role;*/

   /* @ManyToMany
    private Set<Role> roles ;*/
}
