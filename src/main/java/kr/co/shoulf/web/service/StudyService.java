package kr.co.shoulf.web.service;

import jakarta.transaction.Transactional;
import kr.co.shoulf.web.entity.Studycafe;
import kr.co.shoulf.web.entity.Studyroom;
import kr.co.shoulf.web.entity.StudyroomDetail;
import kr.co.shoulf.web.entity.StudyroomImage;
import kr.co.shoulf.web.repository.StudycafeRepository;
import kr.co.shoulf.web.repository.StudyroomDetailRepository;
import kr.co.shoulf.web.repository.StudyroomImageRepository;
import kr.co.shoulf.web.repository.StudyroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    //스터디 카페 전체 목록
    public List<Studycafe> listCafe() {
        return studycafeRepository.findAll();
    }

    //스터디 카페 고유번호로 찾기
    public Studycafe oneCafe(Long studycafeNo) {
        Optional<Studycafe> result = studycafeRepository.findById(studycafeNo);
        Studycafe studycafe = result.orElseThrow();
        return studycafe;
    }

    //스터디룸 스터디카페 고유번호로 찾기
    public List<Studyroom> listRoom(Long studycafeNo) {
        List<Studyroom> studyroomList = studyroomRepository.findByStudycafe_StudycafeNo(studycafeNo);
        return studyroomList;
    }

    //스터디룸 고유번호로 찾기
    public Studyroom oneRoom(Long studyroomNo) {
        Optional<Studyroom> result = studyroomRepository.findById(studyroomNo);
        Studyroom studyroom = result.orElseThrow();
        return studyroom;
    }
}
