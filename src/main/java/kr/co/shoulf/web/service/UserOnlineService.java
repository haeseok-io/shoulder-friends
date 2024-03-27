package kr.co.shoulf.web.service;

import kr.co.shoulf.web.entity.UserOnline;
import kr.co.shoulf.web.entity.Users;
import kr.co.shoulf.web.repository.UserOnlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserOnlineService {
    private final UserOnlineRepository userOnlineRepository;

    public UserOnline readOne(Users users){
        return userOnlineRepository.findByUsers(users);
    }
}
