package kr.co.shoulf.web.service;

import jakarta.transaction.Transactional;
import kr.co.shoulf.web.entity.Position;
import kr.co.shoulf.web.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PositionService {
    private final PositionRepository positionRepository;

    public List<Position> readAll() {
        return positionRepository.findAll();
    }

    public Position readOne(Integer no) {
        return positionRepository.findById(no).orElse(null);
    }
}




