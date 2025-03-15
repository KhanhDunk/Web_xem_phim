package org.khanhdunk.web_dat_ve_xem_phim.Service.iplm;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.khanhdunk.web_dat_ve_xem_phim.Entity.Role_;
import org.khanhdunk.web_dat_ve_xem_phim.DTO.*;
import org.khanhdunk.web_dat_ve_xem_phim.Entity.Users;
import org.khanhdunk.web_dat_ve_xem_phim.Repository.UsersRepository;
import org.khanhdunk.web_dat_ve_xem_phim.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

@Service
@RequiredArgsConstructor
public class UsersServiceIplm implements UsersService {


    private final UsersRepository usersRepo;

    private final MailService mailService ;

    private final PasswordEncoder encoder ;

    @Autowired
    AuthenticationManager authenticationManager;





    @Override
    public void create(UsersDTO dto) {
        Users entity = new Users();
    }

    @Override
    public void Update(UsersDTO dto) {

    Optional<Users> users= usersRepo.findById(dto.getUserId());

    if(users.isPresent())
    {
        Users user = users.get();

        mapToEntity(user,dto);
        usersRepo.save(user);
    }
    else
    {
        throw new RuntimeException("User không tồn tại với ID: " + dto.getUserId()
        );
    }


    }

    @Override
    public UsersDTO getById(Long id) {
        return usersRepo.findById(id)
                .map(users -> new UsersDTO(
                        users.getUserId(),
                        users.getMa(),
                        users.getUserName(),
                        users.getEmail(),
                        users.getFullName(),
                        users.getPassword(),
                        users.getPhone(),
                        users.getPoint(),
                        users.getGender(),
                        users.getBirthDay(),
                        users.getRole()
                ))
                .orElse(null); // Trả về null nếu không tìm thấy
    }




    @Override
    public List<UsersDTO> getAll(Integer page, Integer size) {



        return null;
    }

    @Override
    public void login(String email, String password) {


        Optional<Users> users = usersRepo.findByEmail(email);

        if(users.isEmpty())
        {
            throw new RuntimeException("Tài khoản đã tồn tại");
        }

        Users user = users.get();

        if(!encoder.matches(password , user.getPassword()))
        {
            throw new RuntimeException("Mật khẩu không chính xác");
        }
        System.out.println("Đăng nhập thành công: " + email);
    }


    @Override

    public ResponseEntity<String> register(UsersDTO dto)
    {

        if(usersRepo.existsByEmail(dto.getEmail()))
        {
            return ResponseEntity.badRequest().body("Email đã được đăng ký");
        }

        String otp = mailService.generateOTP(6);

        String subject = "Xác nhận đăng ký tài khoản";
        String message = "Xin chào " + dto.getFullName() + ",\n\nMã OTP của bạn là: " + otp +
                "\nVui lòng nhập mã này để hoàn tất việc đăng ký.\n\nCảm ơn!";
        mailService.sendMail(dto.getEmail(), subject, message);


        Users newUsers = new Users() ;
        mapToEntity(newUsers ,dto);

        try {
            usersRepo.save(newUsers);
            return ResponseEntity.ok("Đăng ký thành công, vui lòng kiểm tra email để nhận mã OTP.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi lưu dữ liệu: " + e.getMessage());
        }

    }




    private void mapToEntity(Users entity , UsersDTO dto)
    {
        entity.setUserName(dto.getUserName());
        entity.setMa(dto.getMa());
        entity.setEmail(dto.getEmail());
        entity.setFullName(dto.getFullName());
        entity.setPassword(encoder.encode(dto.getPassword()));
        entity.setBirthDay(dto.getBirthDay());
        entity.setPhone(dto.getPhone());
        entity.setPoint(dto.getPoint());
        entity.setGender(dto.getGender());
        entity.setCreateAt(LocalDate.now());
        entity.setRole((Role_.USER) ) ;
        entity.setAvatar(null);
    }


   /* @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepo.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getUserName()))
        );
    }*/

}
