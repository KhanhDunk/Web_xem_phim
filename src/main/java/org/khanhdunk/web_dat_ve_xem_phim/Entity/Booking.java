package org.khanhdunk.web_dat_ve_xem_phim.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long BookingId ;

    private LocalDateTime BookingDate ;
    private Float TotalPrice ;
    private String StatusBooking ;

    @ManyToOne
    @JoinColumn(name = "User_Id")
    private Users users ;


}
