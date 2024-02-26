package kr.co.shoulf.web.service;

import jakarta.transaction.Transactional;
import kr.co.shoulf.web.entity.PositionDetail;
import kr.co.shoulf.web.repository.PositionDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PositionService {
    private final PositionDetailRepository positionDetailRepository;

    public List<PositionDetail> readAll() { // 직무정보 전체가져오기
        List<PositionDetail> result = positionDetailRepository.findAll();

        return result;
    }

    public PositionDetail readOne(int no) {
        Optional<PositionDetail> result = positionDetailRepository.findById(no);
        PositionDetail positionDetail = result.orElseThrow();
        return positionDetail;

    }
}




