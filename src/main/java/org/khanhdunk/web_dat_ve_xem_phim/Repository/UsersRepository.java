package org.khanhdunk.web_dat_ve_xem_phim.Repository;

import org.khanhdunk.web_dat_ve_xem_phim.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
/*  SELECT * FROM users WHERE user_name = ?;*/ // SQL triển khai dòng lệnh này


  Optional<Users>findByMa(String ma);
  Optional<Users>findById(Long userId);
  Users findByUserName(String userName) ;

  boolean existsByUserName(String username);
  boolean existsByEmail(String email);


  Optional<Users> findByEmail(String email) ;
}