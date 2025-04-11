package org.khanhdunk.web_dat_ve_xem_phim.Service;

import com.nimbusds.jose.JOSEException;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.AuthenticationRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.AuthenticationResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.IntrospectRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.IntrospectResponse;

import java.text.ParseException;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest request) ;

    IntrospectResponse introspect(IntrospectRequest request ) throws JOSEException, ParseException;
}
