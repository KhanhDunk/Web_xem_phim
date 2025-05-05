package org.khanhdunk.web_dat_ve_xem_phim.Service;

import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.PermissionRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.PermissionResponse;

import java.util.List;

public interface PermissionService {

    PermissionResponse create(PermissionRequest request);

    List<PermissionResponse> getAll()    ;

    void delete(String permission);
}
