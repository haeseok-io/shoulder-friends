package kr.co.shoulf.web.service;

import kr.co.shoulf.web.entity.Platform;
import kr.co.shoulf.web.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlatformService {
    private final PlatformRepository platformRepository;

    public List<Platform> readAll() {
        return platformRepository.findAll();
    }
}
