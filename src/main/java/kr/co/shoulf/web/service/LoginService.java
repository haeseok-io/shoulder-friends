package kr.co.shoulf.web.service;

import kr.co.shoulf.web.entity.UserDetail;
import kr.co.shoulf.web.entity.Users;
import kr.co.shoulf.web.repository.UserDetailRepository;
import kr.co.shoulf.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final PasswordEncoder passwordEncoder;
    public UserDetails login(Users users) {
        Users readUser = userRepository.findByEmail(users.getEmail());
        System.out.println(readUser);

        if (readUser == null) {
            throw new UsernameNotFoundException("가입된 이메일을 찾을 수 없습니다. " + users.getEmail());
        }

        // UserDetails 객체를 생성하여 반환
        return User.withUsername(readUser.getEmail())
                .password(readUser.getPass())
                .roles(readUser.getRole()) // 사용자 권한 설정
                .build();
    }

    public void registerUser(Users user) {
        // 이메일이 이미 존재하는지 확인
        boolean isUser = userRepository.existsByEmail(user.getEmail());
        if(isUser) {
            return;
        }

        Users newUser = new Users();

        newUser.setEmail(user.getEmail());
        newUser.setPass(passwordEncoder.encode(user.getPass()));
        newUser.setNickname(user.getNickname());
        newUser.setRole("ROLE_USER");

        UserDetail userDetail = UserDetail.builder()
                .users(newUser)
                .build();

        userDetailRepository.save(userDetail);

    }

}
