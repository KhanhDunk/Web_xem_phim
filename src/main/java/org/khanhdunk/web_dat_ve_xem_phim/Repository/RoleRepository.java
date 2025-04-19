package org.khanhdunk.web_dat_ve_xem_phim.Repository;

import org.khanhdunk.web_dat_ve_xem_phim.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {

}
