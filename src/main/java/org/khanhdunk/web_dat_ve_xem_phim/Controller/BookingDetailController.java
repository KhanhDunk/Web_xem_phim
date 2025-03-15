package org.khanhdunk.web_dat_ve_xem_phim.Controller;

import lombok.RequiredArgsConstructor;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.BookingDetailDTO;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Users;
import org.khanhdunk.web_dat_ve_xem_phim.Service.BookingDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*@RequestMapping("api/bookingDetail")
@RestController
@RequiredArgsConstructor // Tự động tạo contructor có final hoặc @NotNull
public class BookingDetailController {
    private final BookingDetailService bookingDetailService ;

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<BookingDetailDTO>> getBookingHistory(@PathVariable Long userId) {
        List<BookingDetailDTO> history = bookingDetailService.getBookingHistory(userId);

        if (history.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(history);
    }
}*/
