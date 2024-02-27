package kr.co.shoulf.web.service;

import kr.co.shoulf.web.entity.Online;
import kr.co.shoulf.web.repository.OnlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OnlineService {
    private final OnlineRepository onlineRepository;

    public List<Online> readAll() {
        return onlineRepository.findAll();
    }
}
