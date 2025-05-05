package org.khanhdunk.web_dat_ve_xem_phim.Mapper;

import org.khanhdunk.web_dat_ve_xem_phim.DTO.CategoriesDTO;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Categories;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriesMapper {

    Categories CategoriesToDTO(CategoriesDTO dto);

}
