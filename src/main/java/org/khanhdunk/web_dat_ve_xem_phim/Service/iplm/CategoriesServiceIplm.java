package org.khanhdunk.web_dat_ve_xem_phim.Service.iplm;

import org.khanhdunk.web_dat_ve_xem_phim.DTO.CategoriesDTO;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Categories;
import org.khanhdunk.web_dat_ve_xem_phim.Mapper.CategoriesMapper;
import org.khanhdunk.web_dat_ve_xem_phim.Repository.CategoriesRepository;
import org.khanhdunk.web_dat_ve_xem_phim.Service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriesServiceIplm implements CategoriesService {

    @Autowired
     private CategoriesRepository categoriesRepo ;


    @Autowired
    private CategoriesMapper categoriesMapper ;

    @Override
    public Categories createCategories(CategoriesDTO dto ) {

        if(categoriesRepo.findByName(dto.getName()).isPresent())
        {
            throw new RuntimeException("Loại phim đã tồn tại");
        }

      Categories categories =   categoriesMapper.CategoriesToDTO(dto) ;


        return categoriesRepo.save(categories);
    }
}
