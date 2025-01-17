package org.khanhdunk.web_dat_ve_xem_phim.Service.iplm;

import lombok.RequiredArgsConstructor;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.LoginRequest;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.LoginResponse;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.UsersDTO;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Users;
import org.khanhdunk.web_dat_ve_xem_phim.Repository.UsersRepository;
import org.khanhdunk.web_dat_ve_xem_phim.Service.UsersService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceIplm implements UsersService {


    private final UsersRepository usersRepo;

    @Override
    public void create(UsersDTO dto) {
        Users entity = new Users();
    }

    @Override
    public void Update(UsersDTO dto) {

    }

    @Override
    public UsersDTO getById(Long id) {



        return null;
    }

    @Override
    public List<UsersDTO> getAll(Integer page, Integer size) {



        return null;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest)
    {
        Users users = usersRepo.findByUserName(loginRequest.getUserName());
        if(users == null )
        {
            return new LoginResponse("Invalid username") ;

        }
        if(!users.getPassword().equals(loginRequest.getPassword()))
        {
            return new LoginResponse("Invalid password") ;
        }

        return new LoginResponse("Login successful");
    }

    private void mapToEntity(Users entity , UsersDTO dto)
    {
        entity.setUserName(dto.getUserName());
        entity.setEmail(dto.getEmail());
        entity.setFullName(dto.getFullName());
        entity.setPassword(dto.getPassword());
        entity.setPhone(dto.getPhone());
        entity.setPoint(dto.getPoint());
        entity.setGender(dto.getGender());
    }
}
