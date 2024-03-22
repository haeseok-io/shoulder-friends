package kr.co.shoulf.web.service;

import kr.co.shoulf.web.entity.UserJob;
import kr.co.shoulf.web.entity.Users;
import kr.co.shoulf.web.repository.UserJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserJobService {
    private final UserJobRepository userJobRepository;

    public UserJob readById(Users users) {
        return userJobRepository.findByUsers(users);
    }
}
