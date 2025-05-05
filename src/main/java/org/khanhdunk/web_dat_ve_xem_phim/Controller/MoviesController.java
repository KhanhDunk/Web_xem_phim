package org.khanhdunk.web_dat_ve_xem_phim.Controller;

import org.khanhdunk.web_dat_ve_xem_phim.DTO.MoviesDTO;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.MoviesRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.MoviesResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.ResponseDTO;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Movies;
import org.khanhdunk.web_dat_ve_xem_phim.Service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies/")
public class MoviesController {

    @Autowired
    private MoviesService moviesService ;

    @PostMapping("/createMovies")
    private ResponseDTO<Movies> createMovies(@RequestBody  MoviesDTO dto)
    {
        //Dùng requestBody mới biết được gửi kiểu json xuống nếu kh sẽ kh biết
        var movies = moviesService.createMovies(dto);
        return ResponseDTO.<Movies>builder()
                .message("Tạo phim mới thành công")
                .data(movies)
                .status(HttpStatus.OK)
                .build();
    }

    @PutMapping("/updateMovies/{movieId}")
    private ResponseDTO<MoviesResponse> updateMovies(@RequestBody MoviesRequest request, @PathVariable Long movieId)
    {
        MoviesResponse movies = moviesService.updateMovies(request, movieId);
        return ResponseDTO.<MoviesResponse>builder()
                .message("Cập nhật phim thành công")
                .data(movies)
                .status(HttpStatus.OK)
                .build();
    }


    @GetMapping("/getMovies")
    private ResponseDTO<List<Movies>> getMovies()
    {
        List<Movies> moviesList = moviesService.getMovies();
        return ResponseDTO.<List<Movies>>builder()
                .message("Lấy danh sách phim thành công")
                .data(moviesList)
                .status(HttpStatus.OK)
                .build();
    }


    @GetMapping("/getMovies/{movieId}")
    private ResponseDTO<Movies> getMovieId(@PathVariable Long movieId)
    {
        Movies getMovieId = moviesService.getMovieId(movieId);
        return ResponseDTO.<Movies>builder()
                .message("Lấy id ")
                .status(HttpStatus.OK)
                .data(getMovieId)
                .build();
    }



    @GetMapping("/pageMovies")
    public Page<Movies> getMovies(@RequestParam int page, @RequestParam int size) {
        return moviesService.getMovies(page, size);
    }
}
