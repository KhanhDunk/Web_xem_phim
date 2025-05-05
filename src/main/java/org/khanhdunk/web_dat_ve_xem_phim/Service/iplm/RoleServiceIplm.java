package org.khanhdunk.web_dat_ve_xem_phim.Service.iplm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.RoleRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.RoleResponse;
import org.khanhdunk.web_dat_ve_xem_phim.Mapper.RoleMapper;
import org.khanhdunk.web_dat_ve_xem_phim.Repository.PermissionRepository;
import org.khanhdunk.web_dat_ve_xem_phim.Repository.RoleRepository;
import org.khanhdunk.web_dat_ve_xem_phim.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleServiceIplm implements RoleService {

    @Autowired
    RoleRepository roleRepo ;
    @Autowired
    PermissionRepository permissionRepo ;
    @Autowired
    RoleMapper  roleMapper ;

    @Override
    public RoleResponse create(RoleRequest request) {
        var role = roleMapper.toRole(request);

        if (request.getPermissions() != null && !request.getPermissions().isEmpty()) {
            var permissions = permissionRepo.findAllById(request.getPermissions());
            role.setPermissions(new HashSet<>(permissions));
        } else {
            role.setPermissions(new HashSet<>()); // hoặc null nếu bạn cho phép
        }

        role = roleRepo.save(role);
        return roleMapper.toRoleResponse(role);
    }


    @Override
    public List<RoleResponse> getAll()
    {
        return  roleRepo.findAll()
                .stream()
                .map(roleMapper::toRoleResponse).toList();
    }

    public void delete(String role)
    {
        roleRepo.deleteById(role);
    }
}
