package org.khanhdunk.web_dat_ve_xem_phim.Mapper;

import org.khanhdunk.web_dat_ve_xem_phim.DTO.WatchHistoryDTO;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.WatchHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface WatchHistoryMapper {

    @Mapping(source="userId.userId",target="userId")
    @Mapping(source="userId.userName", target="userName")
    @Mapping(source = "moviesId" , target = "moviesdto")
    // Trường moviesId trong WatchHistory sẽ được ánh xạ sang trường moviesdto trong WatchHistoryDTO.

    void WatchHistorytoDTO( WatchHistory watchHistory ,@MappingTarget WatchHistoryDTO dto);

//    @MappingTarget truyền dữ liệu vào đối tượng đó
}
