package org.khanhdunk.web_dat_ve_xem_phim.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.CategoriesDTO;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoviesResponse {
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



    Set<CategoriesDTO> categories ;


}
