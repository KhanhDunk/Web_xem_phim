package org.khanhdunk.web_dat_ve_xem_phim.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Ratings {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ratingId ;

    @Min(1)
    @Max(5)
    Long rating ;

    LocalDateTime createAt ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    Users userId ;

    @ManyToOne
    @JoinColumn(name= "movie_id")
    Movies moviesId;

}
