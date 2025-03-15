package org.khanhdunk.web_dat_ve_xem_phim.Service;

import org.khanhdunk.web_dat_ve_xem_phim.DTO.BookingDTO;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.BookingDetailDTO;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Users;

import java.util.List;

public interface BookingDetailService {

    List<BookingDetailDTO> getBookingHistory(Long UserId);
}
