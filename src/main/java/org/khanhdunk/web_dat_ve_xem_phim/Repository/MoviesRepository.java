package org.khanhdunk.web_dat_ve_xem_phim.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface MoviesRepository extends JpaRepository<Movies,Long> {

    Optional<Movies>findById(Long movieId) ;

    Page<Movies>findAll(Pageable pageable );

    Optional<Movies>findByTitle(String title);
}
