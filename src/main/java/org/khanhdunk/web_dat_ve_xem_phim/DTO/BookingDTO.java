package org.khanhdunk.web_dat_ve_xem_phim.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {


    private Long userId ;
    private Long BookingId ;
    private Float TotalPrice ;
    private LocalDateTime BookingDate ;
}
