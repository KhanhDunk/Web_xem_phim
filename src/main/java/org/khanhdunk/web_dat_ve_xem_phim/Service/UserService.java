package org.khanhdunk.web_dat_ve_xem_phim.Service;

import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.RegisterRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.ResetPasswordRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.UserUpdateRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.RegisterResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.ResetPasswordResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.RoleResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.UserResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.UsersDTO;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Role;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Users;

import java.util.List;
import java.util.Set;

public interface UserService {


    Users create(UsersDTO dto) ;

    UserResponse update(UserUpdateRequest request, Long userId) ;

    Set<RoleResponse> mapRolesToRoleResponses(Set<Role> roles);

    void delete(Long userId);

    List<UserResponse> getUsers();

    Users getUserId(Long id);

    UsersDTO getMyInfo() ;

    RegisterResponse register(RegisterRequest request );

    ResetPasswordResponse resetPassword (ResetPasswordRequest request);
}
