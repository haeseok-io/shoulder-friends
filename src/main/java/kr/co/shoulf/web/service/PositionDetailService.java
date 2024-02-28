package kr.co.shoulf.web.service;

import kr.co.shoulf.web.entity.PositionDetail;
import kr.co.shoulf.web.repository.PositionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionDetailService {
    private final PositionDetailRepository positionDetailRepository;

    public List<PositionDetail> readAll() {
        return positionDetailRepository.findAll();
    }

    public PositionDetail readOne(Integer no) {
        return positionDetailRepository.findById(no).orElse(null);
    }

    public List<PositionDetail> readPositionDetail(Integer positionNo) {
        return positionDetailRepository.findByPosition_PositionNo(positionNo);
    }
}
