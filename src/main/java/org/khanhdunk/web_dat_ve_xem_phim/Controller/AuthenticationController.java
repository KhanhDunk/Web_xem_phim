package org.khanhdunk.web_dat_ve_xem_phim.Controller;



import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.*;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.AuthenticationRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.IntrospectRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.AuthenticationResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.IntrospectResponse;
import org.khanhdunk.web_dat_ve_xem_phim.Service.iplm.AuthenticationServiceIplm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping ("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true )
public class AuthenticationController {


    final AuthenticationServiceIplm authenticate ;
    @PostMapping("/token")
    ResponseDTO<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request)
    {
      var result =  authenticate.authenticate(request) ;

        return ResponseDTO.<AuthenticationResponse>builder()
                .data(result)
                .build();
    }


    @PostMapping("/instrospect")
    ResponseDTO<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result =  authenticate.introspect(request) ;
        return ResponseDTO.<IntrospectResponse>builder()
                .data(result)
                .build();
    }






}
