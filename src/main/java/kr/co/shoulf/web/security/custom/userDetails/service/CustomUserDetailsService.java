package kr.co.shoulf.web.security.custom.userDetails.service;

import kr.co.shoulf.web.entity.Users;
import kr.co.shoulf.web.repository.UserRepository;
import kr.co.shoulf.web.security.custom.userDetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("아이디 정보가 존재하지 않습니다.");
        }

        return new CustomUserDetails(user);
    }
}
