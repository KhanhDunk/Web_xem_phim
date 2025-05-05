package org.khanhdunk.web_dat_ve_xem_phim.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Comments {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long commentId ;

    @Column(columnDefinition = "TEXT")
    String content ;

    LocalDateTime createdAt ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    Users userId ;


    @ManyToOne
    @JoinColumn(name ="movie_id")
    Movies movieId ;
}
