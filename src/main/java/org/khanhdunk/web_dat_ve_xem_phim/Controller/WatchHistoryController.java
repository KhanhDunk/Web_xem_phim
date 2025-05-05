package org.khanhdunk.web_dat_ve_xem_phim.Controller;

import lombok.extern.slf4j.Slf4j;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.ResponseDTO;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.WatchHistoryDTO;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.WatchHistory;
import org.khanhdunk.web_dat_ve_xem_phim.Service.WatchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/watchHistory")
public class WatchHistoryController{

    @Autowired
    private WatchHistoryService watchHistoryService  ;

        @GetMapping("/getAll")
        public ResponseDTO<List<WatchHistoryDTO>> getAll()
        {
            var getWatchHistory =  watchHistoryService.getAll();
            return ResponseDTO.<List<WatchHistoryDTO>>builder()
                    .data(getWatchHistory)
                    .status(HttpStatus.OK)
                    .message("Lấy lịch sử xem thành công")
                    .build();
        }

}
