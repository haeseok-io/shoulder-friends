package kr.co.shoulf.web.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import kr.co.shoulf.web.dto.StudycafeDTO;
import kr.co.shoulf.web.entity.*;
import kr.co.shoulf.web.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
    private final ReservationRepository reservationRepository;

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

    //스터디카페 삭제
    public void deletecafe(Long studycafeNo) {
        studycafeRepository.deleteById(studycafeNo);
    }

    //스터디룸 삭제
    public void deleteRoom(Long studyroomNo) {
        Optional<Studyroom> result = studyroomRepository.findById(studyroomNo);
        // 스터디룸 번호에 해당하는 예약 리스트 가져오기
        List<Reservation> reservationList = reservationRepository.findByStudyroom_StudyroomNo(studyroomNo);
        if (result.isPresent()) {
            Studyroom studyroom = result.get();
            // 스터디룸과 연관된 예약 데이터의 스터디룸 연관 관계 끊기
            for (Reservation reservation : reservationList) {
                if (reservation.getStudyroom() != null) {
                    reservation.setStudyroom(null); // 스터디룸과의 연관 관계 끊기
                    reservationRepository.save(reservation); // 변경된 예약 데이터 저장
                }
            }
            // 연관된 스터디룸디테일을 삭제
            if (studyroom.getStudyroomDetail() != null) {
                studyroom.setStudyroomDetail(null); // 스터디룸과의 연관 관계를 끊음
            }
            studyroomDetailRepository.deleteById(studyroomNo); // 스터디룸 디테일 삭제
            studyroomRepository.delete(studyroom); // 스터디룸 삭제
        }
    }

    //스터디룸에 남은 예약이 있는지 확인
    public List<Reservation> reservCheck(Long studyroomNo) {
        List<Reservation> list = new ArrayList<>();
        List<Reservation> reservationList = reservationRepository.findByStudyroom_StudyroomNo(studyroomNo);
        for (Reservation r : reservationList){
            Date d1 = null;
            Date d2 = null;

            try {
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String datenow = sdf.format(d);
                String ckin = sdf.format(r.getCheckin());
                d1 = sdf.parse(datenow);
                d2 = sdf.parse(ckin);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            //승인 상태이고 날짜가 안 지난 예약만 list에 넣기
            if(r.getStatus() == 1 && d1.compareTo(d2) < 0){
                list.add(r);
            }
        }
        return list;
    }

    //스터디룸 예약 안된 남은 방찾기
    public List<Studyroom> ckeckinlistRoom(Long studycafeNo, String ckeckin) {
        List<Studyroom> studyroomList = studyroomRepository.findByStudycafe_StudycafeNo(studycafeNo);
        List<Studyroom> studyroomLists = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(ckeckin);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        List<Reservation> reservations = reservationRepository.findByCheckinAndStatus(date, 1);

        Studyroom studyrooms = null;

        for (Reservation reservation : reservations) {
            if (reservation.getCheckin().equals(date)) {
                // 예약 상태가 1이고 체크인 날짜와 미팅 요청 날짜가 겹치는 경우 해당 스터디룸 제외
                studyrooms = reservation.getStudyroom();
                continue;
            } else {
                // 예약 상태가 2이거나 체크인 날짜가 겹치지 않는 경우 해당 스터디룸 추가
                reservations.add(reservation);
            }
        }

        for(Studyroom studyroom : studyroomList){
            if(studyrooms != null) {
                if (studyroom.getStudyroomNo() != studyrooms.getStudyroomNo()) {
                    studyroomLists.add(studyroom);
                }
            } else {
                return studyroomList;
            }
        }

        return studyroomLists;
    }

    //예약 안된 남은 방 이미지 목록 가져오기
    public List<StudyroomImage> ckeckinlistRoomImg(Long studycafeNo, String ckeckin){
        List<StudyroomImage> studyroomImageList = new ArrayList<>();
        List<Studyroom> studyroomList = ckeckinlistRoom(studycafeNo, ckeckin);

        studyroomList.forEach(studyroom -> {
            studyroomImageRepository.findByStudyroom(studyroom).forEach(studyroomImage -> {
                studyroomImageList.add(studyroomImage);
            });
        });
        return studyroomImageList;
    }
}
