package kr.co.shoulf.web.service;

import jakarta.transaction.Transactional;
import kr.co.shoulf.web.dto.StudycafeDTO;
import kr.co.shoulf.web.entity.*;
import kr.co.shoulf.web.repository.*;
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

    //스터디카페 번호로 이미지 목록 가져오기
    public List<StudyroomImage> listRoomImg(Long studycafeNo){
        List<StudyroomImage> studyroomImageList = new ArrayList<>();
        studyroomRepository.findByStudycafe_StudycafeNo(studycafeNo).forEach(studyroom -> {
            studyroomImageRepository.findByStudyroom(studyroom).forEach(studyroomImage -> {
                studyroomImageList.add(studyroomImage);
            });
        });
        return studyroomImageList;
    }

    //스터디룸 고유번호로 찾기
    public Studyroom oneRoom(Long studyroomNo) {
        Optional<Studyroom> result = studyroomRepository.findById(studyroomNo);
        Studyroom studyroom = result.orElseThrow();
        return studyroom;
    }

    //스터디룸 번호로 이미지 리스트 찾기
    public List<StudyroomImage> oneRoomImg(Long studyroomNo) {
        List<StudyroomImage> studyroomImageList = studyroomImageRepository.findByStudyroom_StudyroomNo(studyroomNo);
        return studyroomImageList;
    }

    //카페 정보 저장,수정
    public void saveCafe(Studycafe studycafe) {
        studycafeRepository.save(studycafe);
    }
    
    //스터디룸 정보 저장,수정
    public void saveroom(Studyroom studyroom) {
        studyroomRepository.save(studyroom);
    }
    
    //스터디룸 디테일 저장,수정
    public void saveroomDetail(StudyroomDetail studyroomDetail) {
        studyroomDetailRepository.save(studyroomDetail);
    }

    //스터디룸 이미지 저장,수정
    public void saveroomImg(StudyroomImage studyroomImage) {
        studyroomImageRepository.save(studyroomImage);
    }

    //스터디룸 이미지 룸번호로 삭제
    public void deleteImg(Studyroom studyroom) {
        studyroomImageRepository.deleteByStudyroom_StudyroomNo(studyroom.getStudyroomNo());
    }

}
