package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting,Long> {
    //모임 번호로 미팅 리스트 찾기
    List<Meeting> findByMoim_MoimNo(Long moimNo);
    //모임번호랑 미팅일로 미팅 찾기
    Meeting findByMeetDateAndMoim_MoimNo(Date meetDate, Long moimNo);

}
