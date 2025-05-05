package org.khanhdunk.web_dat_ve_xem_phim.Service;

import org.khanhdunk.web_dat_ve_xem_phim.DTO.MoviesDTO;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.MoviesRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.MoviesResponse;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Movies;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MoviesService {


    List<Movies> getMovies();

    Movies getMovieId(Long id);

    Page<Movies> getMovies(int page, int size);


    Movies createMovies(MoviesDTO dto);


    void deleteMovies(Long moviesId);

    MoviesResponse updateMovies(MoviesRequest request, Long movieId);


}
