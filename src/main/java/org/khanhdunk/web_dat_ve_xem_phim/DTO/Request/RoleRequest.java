package org.khanhdunk.web_dat_ve_xem_phim.DTO.Request;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRequest {

    String name;
    String description;
    Set<String> permissions;

}
