package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Moim;
import kr.co.shoulf.web.entity.MoimHeadcount;
import kr.co.shoulf.web.entity.MoimParticipants;
import kr.co.shoulf.web.entity.MoimParticipantsReject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MoimParticipantsRejectRepositoryTest {
    @Autowired
    private MoimParticipantsRejectRepository moimParticipantsRejectRepository;
    @Autowired
    private MoimParticipantsRepository moimParticipantsRepository;

    @Test
    @DisplayName("지원자 거절 사유 추가")
    void testInsertMoimParticipantsReject() {
        ArrayList<MoimParticipantsReject> test = new ArrayList<>();
        List<MoimParticipants> moimParticipants = moimParticipantsRepository.findAll();

        test.add(MoimParticipantsReject.builder().moimParticipants(moimParticipants.get(0)).contents("거절합니다.").build());

        test.forEach(moimParticipantsReject -> moimParticipantsRejectRepository.save(moimParticipantsReject));
    }
}