package org.khanhdunk.web_dat_ve_xem_phim.Repository;


import org.khanhdunk.web_dat_ve_xem_phim.Entity.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permissions, String> {
}
