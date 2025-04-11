package org.khanhdunk.web_dat_ve_xem_phim.Service;


import lombok.RequiredArgsConstructor;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.*;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Users;
import org.springframework.http.ResponseEntity;
/*import org.springframework.security.core.userdetails.UserDetails;*/
import org.springframework.stereotype.Service;

import java.util.List;


public interface UsersService {

    Users create (UsersDTO dto);

    Users update (UsersDTO dto , Long userId);

    void delete(Long userId);
    List<Users> getUser();


    Users getUserId(Long id);

}
