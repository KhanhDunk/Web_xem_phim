package org.khanhdunk.web_dat_ve_xem_phim.Mapper;


import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.RoleRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.PermissionResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.RoleResponse;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
//MapStruct sẽ tự động generate class implement UserMapper, và đánh dấu class đó là một Spring Bean (@Component). Điều này giúp bạn có thể inject nó vào nơi khác
public interface RoleMapper {

    @Mapping(target = "permissions" , ignore = true) // bỏ qua trường permissions khi map
    Role toRole (RoleRequest request);


    RoleResponse toRoleResponse(Role role ) ;

//    default PermissionResponse map(Permission permission) {
//        PermissionResponse response = new PermissionResponse();
//        response.setName(permission.getName());
//        response.setDescripsion(permission.getDescription());
//        return response;
//    }
}
