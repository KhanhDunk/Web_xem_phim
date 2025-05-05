package org.khanhdunk.web_dat_ve_xem_phim.Mapper;

import org.khanhdunk.web_dat_ve_xem_phim.DTO.MoviesDTO;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.MoviesRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.MoviesResponse;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Movies;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MoviesMapper {

        Movies toMovies (MoviesDTO dto) ;

        void UpdateMovie(Movies movies , @MappingTarget MoviesDTO dto);


//        @Mapping(target =   "movieCatagories" , ignore = true  )
      void  toResponse(Movies movies , @MappingTarget MoviesResponse response );

        void toEntity(@MappingTarget Movies movies , MoviesRequest request ) ;
}
