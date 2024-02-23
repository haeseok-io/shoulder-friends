package kr.co.shoulf.web.service;

import jakarta.transaction.Transactional;
import kr.co.shoulf.web.entity.Studycafe;
import kr.co.shoulf.web.entity.Studyroom;
import kr.co.shoulf.web.entity.StudyroomDetail;
import kr.co.shoulf.web.repository.StudycafeRepository;
import kr.co.shoulf.web.repository.StudyroomDetailRepository;
import kr.co.shoulf.web.repository.StudyroomImageRepository;
import kr.co.shoulf.web.repository.StudyroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StudyService {
    private final StudycafeRepository studycafeRepository;
    private final StudyroomRepository studyroomRepository;
    private final StudyroomDetailRepository studyroomDetailRepository;
    private final StudyroomImageRepository studyroomImageRepository;


    public List<Studycafe> listCafe() {
        return studycafeRepository.findAll();
    }

    public Studycafe oneCafe(Long studycafeNo) {
        Optional<Studycafe> result = studycafeRepository.findById(studycafeNo);
        Studycafe studycafe = result.orElseThrow();
        return studycafe;
    }

    public List<Studyroom> listRoom(Long studycafeNo) {
        List<Studyroom> byStudycafeStudycafeNo = studyroomRepository.findByStudycafe_StudycafeNo(studycafeNo);
        return byStudycafeStudycafeNo;
    }
}
