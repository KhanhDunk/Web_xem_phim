package org.khanhdunk.web_dat_ve_xem_phim.Service;

import com.nimbusds.jose.JOSEException;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.AuthenticationRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.IntrospectRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.AuthenticationResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.IntrospectResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.ResponseDTO;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.WatchHistoryDTO;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.WatchHistory;

import java.text.ParseException;
import java.util.List;

public interface WatchHistoryService {




    List<WatchHistoryDTO> getAll();
}
