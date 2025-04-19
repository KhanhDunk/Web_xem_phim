package org.khanhdunk.web_dat_ve_xem_phim.DTO.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    Long   userId;
    String userName;
    LocalDate birthDay ;
    Set<RoleResponse> role;
}
