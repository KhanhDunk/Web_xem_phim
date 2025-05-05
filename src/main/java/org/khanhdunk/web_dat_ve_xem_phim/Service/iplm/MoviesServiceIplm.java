package org.khanhdunk.web_dat_ve_xem_phim.Service.iplm;

import lombok.extern.slf4j.Slf4j;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.CategoriesDTO;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.MoviesRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.MoviesResponse;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Categories;
import org.khanhdunk.web_dat_ve_xem_phim.Mapper.MoviesMapper;
import org.khanhdunk.web_dat_ve_xem_phim.Repository.CategoriesRepository;
import org.khanhdunk.web_dat_ve_xem_phim.Service.MoviesService;
import org.springframework.data.domain.Pageable; // Spring Data JPA - đúng
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

import org.khanhdunk.web_dat_ve_xem_phim.DTO.MoviesDTO;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Movies;
import org.khanhdunk.web_dat_ve_xem_phim.Repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MoviesServiceIplm implements MoviesService {


    @Autowired
    private MoviesRepository moviesRepo ;

    @Autowired
    private MoviesMapper moviesMapper ;

    @Autowired
    private CategoriesRepository categoriesRepo ;

    @Override
   public List<Movies> getMovies()
   {
        return moviesRepo.findAll() ;
   }

   @Override
    public Movies getMovieId(Long id)
    {
        return moviesRepo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy phim"));
    }

    @Override
    public Page<Movies> getMovies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);  // Tạo Pageable với trang và kích thước
        return moviesRepo.findAll(pageable);  // Trả về một đối tượng Page chứa danh sách phim
    }

    @Override
    public Movies createMovies(MoviesDTO dto) {

        Movies movies =  moviesMapper.toMovies(dto);




        if (dto.getMovieCategories() != null && !dto.getMovieCategories().isEmpty()) {
            Set<Categories> categories = dto.getMovieCategories().stream()
                    .map(name -> categoriesRepo.findByName(name) // tìm category theo tên
                            .orElseThrow(() -> new RuntimeException("Category not found: " + name)))
                    .collect(Collectors.toSet());
            movies.setMovieCatagories(categories);
        }

        return moviesRepo.save(movies);
    }

    @Override
    public void deleteMovies(Long moviesId) {

    }

    @Override
    public MoviesResponse updateMovies(MoviesRequest request, Long movieId) {

        Movies movies = moviesRepo.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tên phim "));

        moviesMapper.toEntity(movies,request);
        MoviesResponse response = new MoviesResponse() ;

        moviesMapper.toResponse(movies , response);
        // Convert từ Set<Categories> trong entity sang Set<CategoriesDTO>
        if (movies.getMovieCatagories() != null) {
            Set<CategoriesDTO> categoryDTOs = movies.getMovieCatagories().stream()
                    .map(cat -> new CategoriesDTO(cat.getCategoryId(), cat.getName()))
                    .collect(Collectors.toSet());

            response.setCategories(categoryDTOs);
        }

        moviesRepo.save(movies);


        return response ;

    }

}
