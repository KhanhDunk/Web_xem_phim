package org.khanhdunk.web_dat_ve_xem_phim.Service;

import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.RoleRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.RoleResponse;

import java.util.List;

public interface RoleService {


    RoleResponse create(RoleRequest request);

    List<RoleResponse> getAll();

    void delete(String role);
}
