package org.khanhdunk.web_dat_ve_xem_phim.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.PermissionRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.PermissionResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.ResponseDTO;
import org.khanhdunk.web_dat_ve_xem_phim.Service.iplm.PermissionServiceIplm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@Slf4j
public class PermissionController {

    @Autowired
  private PermissionServiceIplm permissionService ;

  @PostMapping("create")
  ResponseDTO<PermissionResponse> create (@RequestBody PermissionRequest request)
  {
      return ResponseDTO.<PermissionResponse>builder()
              .data(permissionService.create(request))
              .build();
  }

    @GetMapping("/get")
    ResponseDTO<List<PermissionResponse>> getAll ()
    {
        return ResponseDTO.<List<PermissionResponse>>builder()
                .data(permissionService.getAll())
                .build();
    }

    @DeleteMapping
    ResponseDTO<Void> delete (@PathVariable String permission)
    {
        permissionService.delete(permission);
        return ResponseDTO.<Void>builder().build();
    }

}
