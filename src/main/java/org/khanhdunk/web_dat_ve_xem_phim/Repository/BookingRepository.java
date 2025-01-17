package org.khanhdunk.web_dat_ve_xem_phim.Repository;

import org.khanhdunk.web_dat_ve_xem_phim.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
}
