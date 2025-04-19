
package org.khanhdunk.web_dat_ve_xem_phim.Config;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Role;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Role_;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Users;
import org.khanhdunk.web_dat_ve_xem_phim.Repository.RoleRepository;
import org.khanhdunk.web_dat_ve_xem_phim.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@Slf4j // Dùng  để cung cấp loger
public class ApplicationInitConfig { // Đây là class cấu hình tự động thêm tài khoản admin vào database nếu chưa có

    @Autowired
    private  PasswordEncoder passwordEncoder ;

    @Autowired
    private RoleRepository roleRepo ;
    @Bean
    ApplicationRunner  applicationRunner (UsersRepository usersRepository ) // ApplicationRunner  là functional interface được chaỵ ngay sau khi ứng dụng khởi động
    {
        return args ->{
            if ( usersRepository.findByUserName("ADMIN").isEmpty())
            {


                Users user = Users.builder()
                        .userName("ADMIN")
                        .email("admin@gmail.com")
                        .fullName("admin")
                        .phone("0934681559")
                        /*.role(Set.of(adminRole))*/
                        .password(passwordEncoder.encode("ADMIN"))
                        .build();

                usersRepository.save(user);
                log.warn("Admin đã tồn tại ");

            }

        };
    }


}

