package org.khanhdunk.web_dat_ve_xem_phim.Repository;

import org.khanhdunk.web_dat_ve_xem_phim.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
  Users findByUserName(String userName) ;
}