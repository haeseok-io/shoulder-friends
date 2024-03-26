package kr.co.shoulf.web.service;

import kr.co.shoulf.web.entity.UserOnline;
import kr.co.shoulf.web.repository.UserOnlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserOnlineService {
    private final UserOnlineRepository userOnlineRepository;

    public List<UserOnline> readAll() {
        return userOnlineRepository.findAll();
    }
}
