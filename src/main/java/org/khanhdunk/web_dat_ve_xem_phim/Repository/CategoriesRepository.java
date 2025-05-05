package org.khanhdunk.web_dat_ve_xem_phim.Repository;

import org.khanhdunk.web_dat_ve_xem_phim.Entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface CategoriesRepository extends JpaRepository<Categories,Long> {

    Optional<Categories> findByName(String name);
    Set<Categories> findByNameIn(Set<String> names);
}
