package org.khanhdunk.web_dat_ve_xem_phim.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.PermissionRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.RoleRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.PermissionResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.RoleResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.ResponseDTO;
import org.khanhdunk.web_dat_ve_xem_phim.Service.PermissionService;
import org.khanhdunk.web_dat_ve_xem_phim.Service.iplm.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@Slf4j

public class RoleController {

    @Autowired
  private RoleService roleService ;

  @PostMapping("/create")
  ResponseDTO<RoleResponse> create (@RequestBody RoleRequest request)
  {
      return ResponseDTO.<RoleResponse>builder()
              .data(roleService.create(request))
              .build();
  }

    @GetMapping("/get")
    ResponseDTO<List<RoleResponse>> getAll ()
    {
        return ResponseDTO.<List<RoleResponse>>builder()
                .data(roleService.getAll())
                .build();
    }

    @DeleteMapping("/delete")
    ResponseDTO<Void> delete (@PathVariable String role)
    {
        roleService.delete(role);
        return ResponseDTO.<Void>builder().build();
    }

}
