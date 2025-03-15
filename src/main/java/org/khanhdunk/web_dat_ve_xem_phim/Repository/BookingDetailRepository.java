package org.khanhdunk.web_dat_ve_xem_phim.Repository;

import org.khanhdunk.web_dat_ve_xem_phim.Entity.Booking;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.BookingDetail;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Users;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingDetailRepository extends JpaRepository<BookingDetail,Long> {


    @Query("SELECT bd " +
            "FROM BookingDetail bd " +
            "WHERE bd.user.userId = :userId")
    List<BookingDetail> findByUserId(@Param("userId") Long userId);

}
