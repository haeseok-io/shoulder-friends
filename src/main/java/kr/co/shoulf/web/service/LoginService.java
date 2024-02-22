package kr.co.shoulf.web.service;

import kr.co.shoulf.web.entity.Users;
import kr.co.shoulf.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;

    public boolean login(Users users) {
        Users readUser = userRepository.findByEmail(users.getEmail());
        System.out.println(readUser);

        if (readUser == null) {
            return false;
        }

        return readUser.getPass().equals(users.getPass());
    }
}
