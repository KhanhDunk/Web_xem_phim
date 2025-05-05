package org.khanhdunk.web_dat_ve_xem_phim.DTO.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class    UserUpdateRequest {



    String password;
    String userName ;


    LocalDate birthday;

    List<String> role;
}
