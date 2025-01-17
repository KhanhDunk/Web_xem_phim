package org.khanhdunk.web_dat_ve_xem_phim.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RoomId ;

    private Integer RoomNumber ;
    private Integer Capacity  ;


    @ManyToOne
    @JoinColumn(name = "Cinema_Id")
    private Cinema cinema ;
}
