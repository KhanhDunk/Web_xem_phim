package org.khanhdunk.web_dat_ve_xem_phim.Service;

import lombok.RequiredArgsConstructor;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.LoginRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.LoginResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.UsersDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UsersService {

    void create (UsersDTO dto);

    void Update (UsersDTO dto);

    UsersDTO getById(Long id);

    List<UsersDTO> getAll (Integer page , Integer size);

    LoginResponse login (LoginRequest loginRequest) ;
}
