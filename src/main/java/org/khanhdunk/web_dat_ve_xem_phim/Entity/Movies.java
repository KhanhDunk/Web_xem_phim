package org.khanhdunk.web_dat_ve_xem_phim.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data // Tự động tạo getter vs setter
@NoArgsConstructor // Tạo constructor không tham số
@AllArgsConstructor // Tự động tạo các constructor vs các tham số
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MoviedId ;

    private String MoviesName;
    private String Type ;
    private Integer Duration ;
    private Date ReleaseDate ;
    private String Description ;
    private String Language ;
    private String Director ;
    private String Poster ;
    private String Trailer ;

}
