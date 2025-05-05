package org.khanhdunk.web_dat_ve_xem_phim.Repository;

import org.khanhdunk.web_dat_ve_xem_phim.Entity.Users;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.WatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
/*  SELECT * FROM users WHERE user_name = ?;*/ // SQL triển khai dòng lệnh này


  Optional<Users>findByMa(String ma);
  Optional<Users>findById(Long userId);


  boolean existsByUserName(String username);
 boolean existsByEmail(String email);
  Optional<Users> findByUserName(String userName);


  Optional<Users> findByEmail(String email) ;


}