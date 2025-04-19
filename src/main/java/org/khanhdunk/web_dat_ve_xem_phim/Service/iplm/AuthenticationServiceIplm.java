package org.khanhdunk.web_dat_ve_xem_phim.Service.iplm;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.khanhdunk.web_dat_ve_xem_phim.Config.JwtProperties;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.AuthenticationRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.AuthenticationResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.IntrospectRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.IntrospectResponse;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Role;

import org.khanhdunk.web_dat_ve_xem_phim.Entity.Users;
import org.khanhdunk.web_dat_ve_xem_phim.Repository.UsersRepository;
import org.khanhdunk.web_dat_ve_xem_phim.Service.AuthenticationService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceIplm implements AuthenticationService {

    private final UsersRepository userRepo;
    private final JwtProperties jwtProperties; // Inject từ cấu hình

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepo.findByUserName(request.getUserName())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        PasswordEncoder encoder = new BCryptPasswordEncoder(10);
        boolean authenticated = encoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated) {
            throw new RuntimeException("Lỗi không xác thực");
        }

        var token = generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    private String generateToken(Users user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserName())
                .issuer("khanhdang.com")
                .issueTime(new Date())
                .expirationTime(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .claim("ROLE",  buildScope(user))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(jwtProperties.getSignerKey().getBytes())); // ký
            return jwsObject.serialize(); // ghép header , payload , signature thành 1 chuỗi
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }




    @Override
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException { // Kiểm tra xem token hợp lệ không
        var token = request.getToken();

        JWSVerifier verifier = new MACVerifier(jwtProperties.getSignerKey().getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        boolean verified = signedJWT.verify(verifier);
        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        return IntrospectResponse.builder()
                .valid(verified && expirationTime.after(new Date()))
                .build();
    }

    private String buildScope(Users user) {
        StringJoiner stringJoiner = new StringJoiner(" "); // StringJoiner dùng để nối chuỗi lại với nhau bằng dấu cách

        if (!CollectionUtils.isEmpty(user.getRole()))
            user.getRole().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if (!CollectionUtils.isEmpty(role.getPermissions()))
                    role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
            });

        return stringJoiner.toString();
    }


    }



