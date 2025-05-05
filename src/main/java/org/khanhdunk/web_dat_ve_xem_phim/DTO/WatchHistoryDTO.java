package org.khanhdunk.web_dat_ve_xem_phim.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Movies;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WatchHistoryDTO {

    Long historyId ;
    LocalDateTime watchedAt ;
    Long userId;
    String userName ;
    MoviesDTO moviesdto ;
}
