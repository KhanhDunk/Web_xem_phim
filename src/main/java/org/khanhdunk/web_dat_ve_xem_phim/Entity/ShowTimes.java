package org.khanhdunk.web_dat_ve_xem_phim.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowTimes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ShowTimeId ;

    private LocalDateTime StartTime ;
    private LocalDateTime EndTime ;

    @OneToOne
    @JoinColumn(name="Room_Id")// Tên cột trong bảng giữ khoá ngoại của bảng ShowTimes đến bảng Room
    private Rooms room ;


    @ManyToOne
    @JoinColumn(name = "Movies_Id")
    private Movies movies ;
}
