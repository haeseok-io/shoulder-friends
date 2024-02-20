package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MoimHeadcountRepositoryTest {
    @Autowired
    private MoimHeadcountRepository moimHeadcountRepository;
    @Autowired
    MoimRepository moimRepository;
    @Autowired
    PositionDetailRepository positionDetailRepository;

    @Test
    @DisplayName("모임 인원 추가")
    void testInsertMoimHeadcount() {
        ArrayList<MoimHeadcount> test = new ArrayList<>();
        List<Moim> moim = moimRepository.findAll();
        List<PositionDetail> positionDetail = positionDetailRepository.findAll();

        test.add(MoimHeadcount.builder().moim(moim.get(0)).positionDetail(positionDetail.get(16)).personnel(1).build());
        test.add(MoimHeadcount.builder().moim(moim.get(0)).positionDetail(positionDetail.get(22)).personnel(1).build());
        test.add(MoimHeadcount.builder().moim(moim.get(1)).positionDetail(positionDetail.get(18)).personnel(2).build());
        test.add(MoimHeadcount.builder().moim(moim.get(1)).positionDetail(positionDetail.get(22)).personnel(3).build());
        test.add(MoimHeadcount.builder().moim(moim.get(1)).positionDetail(positionDetail.get(8)).personnel(1).build());
        test.add(MoimHeadcount.builder().moim(moim.get(2)).personnel(8).build());
        test.add(MoimHeadcount.builder().moim(moim.get(3)).personnel(8).build());
        test.add(MoimHeadcount.builder().moim(moim.get(4)).positionDetail(positionDetail.get(8)).personnel(1).build());
        test.add(MoimHeadcount.builder().moim(moim.get(4)).positionDetail(positionDetail.get(22)).personnel(2).build());
        test.add(MoimHeadcount.builder().moim(moim.get(4)).positionDetail(positionDetail.get(2)).personnel(1).build());
        test.add(MoimHeadcount.builder().moim(moim.get(5)).positionDetail(positionDetail.get(22)).personnel(2).build());
        test.add(MoimHeadcount.builder().moim(moim.get(6)).personnel(8).build());
        test.add(MoimHeadcount.builder().moim(moim.get(7)).personnel(8).build());
        test.add(MoimHeadcount.builder().moim(moim.get(8)).positionDetail(positionDetail.get(31)).personnel(1).build());
        test.add(MoimHeadcount.builder().moim(moim.get(8)).positionDetail(positionDetail.get(45)).personnel(1).build());
        test.add(MoimHeadcount.builder().moim(moim.get(8)).positionDetail(positionDetail.get(8)).personnel(1).build());
        test.add(MoimHeadcount.builder().moim(moim.get(9)).positionDetail(positionDetail.get(0)).personnel(2).build());
        test.add(MoimHeadcount.builder().moim(moim.get(9)).positionDetail(positionDetail.get(18)).personnel(3).build());
        test.add(MoimHeadcount.builder().moim(moim.get(9)).positionDetail(positionDetail.get(7)).personnel(2).build());
        test.add(MoimHeadcount.builder().moim(moim.get(9)).positionDetail(positionDetail.get(22)).personnel(2).build());

        test.forEach(moimHeadcount -> moimHeadcountRepository.save(moimHeadcount));
    }
}