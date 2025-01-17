package org.khanhdunk.web_dat_ve_xem_phim.Repository;

import org.khanhdunk.web_dat_ve_xem_phim.Entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesRepository extends JpaRepository<Movies,Long> {
}
