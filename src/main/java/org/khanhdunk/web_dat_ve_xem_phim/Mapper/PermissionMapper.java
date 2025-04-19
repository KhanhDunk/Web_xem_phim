package org.khanhdunk.web_dat_ve_xem_phim.Mapper;

import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.PermissionRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.PermissionResponse;

import org.khanhdunk.web_dat_ve_xem_phim.Entity.Permissions;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permissions toPermission(PermissionRequest request) ;
    PermissionResponse toPermissionResponse(Permissions permssion) ;
}
