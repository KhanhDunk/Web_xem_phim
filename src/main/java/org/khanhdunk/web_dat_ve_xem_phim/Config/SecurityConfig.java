package org.khanhdunk.web_dat_ve_xem_phim.Config;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    private final String[] PUBLIC_ENDPOINTS = {"/auth/**", "/user/account/**","/role/**","/permission/**","/movies/**","/watchHistory/**","/categories/**"};



    @Value("${jwt.signer-key}")
    private String signerKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request ->

                        request

                                .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**")
                        .permitAll()

                                .requestMatchers(HttpMethod.POST,PUBLIC_ENDPOINTS).permitAll()// được truy cập tự do

                                .requestMatchers(HttpMethod.GET, PUBLIC_ENDPOINTS).authenticated()// yêu cầu người dùng đăng nhập
                               /* .hasAuthority("ROLE_ADMIN")*/
                               /* .hasRole(Role_.ADMIN.name())*/


                                .anyRequest().authenticated()
                                //SCOPE_ là tiền tố mặc định trong Spring Security khi bạn dùng OAuth2 JWT và scope được đính kèm trong token.
                                //Ví dụ: nếu token chứa "scope": "ADMIN" → thì hasAuthority("SCOPE_ADMIN") sẽ hợp lệ.



                );


        http.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())
                        .jwtAuthenticationConverter(jwtAuthenticationConverter()))


        ); // Dùng để verify token xem được quyền truy cập vào đâu ( Xác thực người dùng )


        return http.build();
    }


    @Bean
    JwtDecoder jwtDecoder() { // giải mã token

        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");


        return NimbusJwtDecoder.withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();

    }


    @Bean
    PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder(10);

    }


    // Hàm dùng để converter chuyển đổi thông tin quyền trong JWT Token VD:ROLE_ADMIN\
    // Tuỳ chỉnh đọc quyền từ JWT
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            String rolesStr = jwt.getClaimAsString("ROLE"); // Lấy chuỗi từ ROLE trong payload
            List<GrantedAuthority> authorities = new ArrayList<>(); /// nơi lưu các quyền của người dùng để Spring dùng sau này.

            if (rolesStr != null && !rolesStr.isBlank()) {
                for (String role : rolesStr.split(" ")) {
                    authorities.add(new SimpleGrantedAuthority(role.trim()));
                }
            }

            return authorities;
        });

        return converter;
    }

        // GrantedAuthority là hasRole hasAuth
    // Dùng được dùng để chuyển thông tin từ JWT thành đối tượng quyền (GrantedAuthority),
    // để Spring Security có thể sử dụng trong việc xác thực và phân quyền người dùng.
}





