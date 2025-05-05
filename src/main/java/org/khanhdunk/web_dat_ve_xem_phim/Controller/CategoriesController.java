package org.khanhdunk.web_dat_ve_xem_phim.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.CategoriesDTO;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.ResponseDTO;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Categories;
import org.khanhdunk.web_dat_ve_xem_phim.Service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true )
public class CategoriesController {

    @Autowired
    CategoriesService categoriesService ;

    @PostMapping("/createCategories")
    ResponseDTO<Categories> create (@RequestBody CategoriesDTO dto )
    {
        var Categories = categoriesService.createCategories(dto);
       return  ResponseDTO.<Categories>builder()
               .status(HttpStatus.OK)
               .message("Tạo thành công thể loại phim")
               .data(Categories)
               .build();
    }
}
