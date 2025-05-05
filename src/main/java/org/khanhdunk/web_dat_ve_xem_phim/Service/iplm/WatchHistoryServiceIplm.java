package org.khanhdunk.web_dat_ve_xem_phim.Service.iplm;

import org.khanhdunk.web_dat_ve_xem_phim.DTO.ResponseDTO;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.WatchHistoryDTO;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.WatchHistory;

import org.khanhdunk.web_dat_ve_xem_phim.Mapper.WatchHistoryMapper;
import org.khanhdunk.web_dat_ve_xem_phim.Repository.WatchHistoryRepository;
import org.khanhdunk.web_dat_ve_xem_phim.Service.WatchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchHistoryServiceIplm implements WatchHistoryService {


    @Autowired
    private  WatchHistoryRepository watchHistoryRepo ;


    @Autowired
    private WatchHistoryMapper watchHistoryMapper ;

    @Override
    public List<WatchHistoryDTO> getAll() {
        List<WatchHistory> histories = watchHistoryRepo.findAll();

        // Dùng mapper để chuyển đổi WatchHistory thành WatchHistoryDTO
        return histories.stream()
                .map(history -> {
                    WatchHistoryDTO dto = new WatchHistoryDTO();
                    watchHistoryMapper.WatchHistorytoDTO(history, dto);  // Ánh xạ
                    return dto;
                })
                .collect(Collectors.toList());// Thu thập kết quả vào một danh sách
    }


}
