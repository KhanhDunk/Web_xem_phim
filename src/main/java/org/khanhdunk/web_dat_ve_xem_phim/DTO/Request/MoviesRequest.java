package org.khanhdunk.web_dat_ve_xem_phim.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.CategoriesDTO;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Categories;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoviesRequest
{

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
