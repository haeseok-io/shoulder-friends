package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    //스터디룸 번호로 예약내역 찾기
    List<Reservation> findByStudyroom_StudyroomNo(Long studyroomNo);
    //체크인 날자와 상태로 예약 리스트 찾기
    List<Reservation> findByCheckinAndStatus(Date checkin, int status);
}
