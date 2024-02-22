package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Meeting;
import kr.co.shoulf.web.entity.Moim;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MeetingRepositoryTest {
    @Autowired
    MeetingRepository meetingRepository;
    @Autowired
    MoimRepository  moimRepository;

    @Test
    void insertData(){
        //Optional<Moim> moim = moimRepository.findById(1L);
        //Meeting meeting = Meeting.builder()
        //        .meetDate(LocalDateTime.parse("2024-03-20T14:30:00"))
        //        .addr("서울 종로구")
        //        .contents("회의")
        //        .moim(moim.get())
        //        .build();
        //meetingRepository.save(meeting);
    }

    @Test
    void selectOne(){
        Meeting meeting = meetingRepository.findAll().stream().findFirst().get();
        assertEquals("회의", meeting.getContents());
    }
}