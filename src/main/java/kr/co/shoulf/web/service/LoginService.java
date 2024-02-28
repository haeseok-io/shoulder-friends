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

    // 사용자 로그인 처리 메서드
    public UserDetails login(Users users) {
        // 이메일로 사용자 정보 조회
        Users readUser = userRepository.findByEmail(users.getEmail());
        System.out.println(readUser);

        // 조회된 사용자가 없을 경우 예외 발생
        if (readUser == null) {
            throw new UsernameNotFoundException("가입된 이메일을 찾을 수 없습니다. " + users.getEmail());
        }

        // UserDetails 객체를 생성하여 반환
        return User.withUsername(readUser.getEmail())
                .password(readUser.getPass())
                .roles(readUser.getRole()) // 사용자 권한 설정
                .build();
    }

    // 사용자 회원가입 처리 메서드
    public void registerUser(Users user) {
        // 이메일이 이미 존재하는지 확인
        boolean isUser = userRepository.existsByEmail(user.getEmail());

        // 이미 존재하는 사용자일 경우 메서드 종료
        if(isUser) {
            return;
        }

        // 새로운 사용자 객체 생성 및 저장
        Users newUser = new Users();
        newUser.setEmail(user.getEmail());
        newUser.setPass(passwordEncoder.encode(user.getPass())); // 비밀번호 암호화
        newUser.setNickname(user.getNickname());
        newUser.setRole("ROLE_USER");

        UserDetail userDetail = UserDetail.builder()
                .users(newUser)
                .build();

        userDetailRepository.save(userDetail);

    }

}
