/*
    package org.khanhdunk.web_dat_ve_xem_phim.Service.iplm;

    import org.khanhdunk.web_dat_ve_xem_phim.Entity.Users;
    import org.khanhdunk.web_dat_ve_xem_phim.Repository.UsersRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.userdetails.User;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;

    import java.util.Collections;
    @Service
    public class UserServiceDetailIplm implements UserDetailsService {


        private final UsersRepository usersRepository;

        @Autowired
        public UserServiceDetailIplm(UsersRepository usersRepository) {
            this.usersRepository = usersRepository;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Users user = usersRepository.findByUserName(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
        }



    }
*/
