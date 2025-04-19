package org.khanhdunk.web_dat_ve_xem_phim.Service.iplm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.PermissionRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.PermissionResponse;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Permissions;
import org.khanhdunk.web_dat_ve_xem_phim.Mapper.PermissionMapper;
import org.khanhdunk.web_dat_ve_xem_phim.Repository.PermissionRepository;
import org.khanhdunk.web_dat_ve_xem_phim.Service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionServiceIplm  implements PermissionService {

    @Autowired
    PermissionRepository permissionRepo;
    @Autowired
    PermissionMapper permissionMapper ;

    public PermissionResponse create(PermissionRequest request) {
       Permissions permission = permissionMapper.toPermission(request);
       permission = permissionRepo.save(permission);
       return permissionMapper.toPermissionResponse(permission);
    }

   public  List<PermissionResponse> getAll()
    {
        var permission = permissionRepo.findAll();
      return   permission.stream().map(permissionMapper::toPermissionResponse).toList();

    }

   public void delete(String permission)
    {
        permissionRepo.deleteById(permission);
    }

}