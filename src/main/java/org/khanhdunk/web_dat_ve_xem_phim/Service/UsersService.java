package org.khanhdunk.web_dat_ve_xem_phim.Service;

import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.*;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UsersService {

    void create (UsersDTO dto);

    void Update (UsersDTO dto);

    UsersDTO getById(Long id);

    List<UsersDTO> getAll (Integer page , Integer size);

   void login (String username , String password);


    ResponseEntity<String> register(UsersDTO dto);
    /* UserDetails loadUserByUsername(String username) ;*/

}
