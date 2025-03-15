package org.khanhdunk.web_dat_ve_xem_phim.Config;

import io.swagger.models.HttpMethod;


import lombok.RequiredArgsConstructor;
/*import org.khanhdunk.web_dat_ve_xem_phim.Service.iplm.CustomUserDetailsService;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {



    /*private final UserDetailsService userDetailsService;*/


   /* @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(null);
    }*/



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http

                .csrf(csrf ->csrf.disable()) //csrf bảo vệ trc contract
                .httpBasic(httpBasic -> httpBasic.disable() )
                .formLogin(httpBasic -> httpBasic.disable())
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Chỉ ADMIN mới vào được /admin/**
                        .requestMatchers("/user/**").hasAnyRole("ADMIN", "USER")// USER và ADMIN đều vào được
                        .requestMatchers("/public/**").permitAll() // Ai cũng vào được
                        .requestMatchers("/api/account/register").permitAll()
                        .requestMatchers("/user/api/bookingDetail/history/{userId}").permitAll()
                        .anyRequest().permitAll()/*.authenticated()*/// tất cả các request phải authenticated mới accept hệ thống
                );


        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")
                .roles("ADMIN")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

/*@Bean
public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder);
    return authProvider;
}*/

  @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
