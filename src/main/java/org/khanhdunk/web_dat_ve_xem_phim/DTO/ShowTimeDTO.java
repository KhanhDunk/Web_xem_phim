package org.khanhdunk.web_dat_ve_xem_phim.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowTimeDTO {

    private LocalDateTime StartTime ;
    private LocalDateTime EndTime ;

}
