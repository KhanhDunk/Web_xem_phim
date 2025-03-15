    package org.khanhdunk.web_dat_ve_xem_phim.DTO;

    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import org.khanhdunk.web_dat_ve_xem_phim.Entity.Seats;
    import org.khanhdunk.web_dat_ve_xem_phim.Entity.ShowTimes;
    import org.khanhdunk.web_dat_ve_xem_phim.Entity.Users;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class BookingDetailDTO {


        private SeatsDTO seats ;
        private Float Price ;
        private ShowTimeDTO showTimes ;

    }
