package org.khanhdunk.web_dat_ve_xem_phim.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long SeatId ;
    private Integer SeatNumber ;
    private String SeatType ;

    @ManyToOne
    @JoinColumn(name = "Room_Id")
    private Rooms rooms ;
}
