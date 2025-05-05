package org.khanhdunk.web_dat_ve_xem_phim.DTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Categories;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MoviesDTO {


    String title ;
    String genre ;
    Long duration ;
    Date releaseDate ;
    String description ;
    String language ;
    String director ;
    String posterUrl ;
    String trailerUrl ;
    String movieUrl ;


    Set<String> movieCategories;
}
