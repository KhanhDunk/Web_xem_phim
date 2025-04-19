package org.khanhdunk.web_dat_ve_xem_phim.Mapper;

import org.khanhdunk.web_dat_ve_xem_phim.DTO.Request.UserUpdateRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.Response.UserResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.UsersDTO;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface UserMapper {
Users toUser(UsersDTO dto);
UsersDTO toUserDTO(Users user);
UserResponse toUserResponse(Users user );

@Mapping(target =   "role" , ignore = true  )
void updateUser(UserUpdateRequest request , @MappingTarget Users entity);
//@MappingTarget dùng để chỉ định rằng tham số đó là đối tượng đích cần được cập nhật — chứ không phải tạo mới.


}
