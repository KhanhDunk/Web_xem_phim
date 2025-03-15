package org.khanhdunk.web_dat_ve_xem_phim.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long BookingDetailId ;
    private Float Price ;

    @ManyToOne
    @JoinColumn(name = "Seat_Id")
    private Seats seats ;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users user ;

    @OneToOne
    @JoinColumn(name = "ShowTime_Id")
    private ShowTimes showTimes ;
}
