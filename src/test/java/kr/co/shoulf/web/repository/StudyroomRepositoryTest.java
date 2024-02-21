package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Studyroom;
import kr.co.shoulf.web.entity.StudyroomDetail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StudyroomRepositoryTest {
    @Autowired
    StudycafeRepository studycafeRepository;
    @Autowired
    StudyroomRepository studyroomRepository;
    @Autowired
    StudyroomDetailRepository studyroomDetailRepository;


    @Test
    @DisplayName("스터디룸, 스터디룸 상세정보 데이터 19개 추가")
    void testInsertStudyroom () {
        ArrayList<Studyroom> studyroom = new ArrayList<>();

        studyroom.add(Studyroom.builder().name("101호").price(40000).studycafe(studycafeRepository.findById(1L).orElse(null)).build());
        studyroom.add(Studyroom.builder().name("102호").price(50000).studycafe(studycafeRepository.findById(1L).orElse(null)).build());
        studyroom.add(Studyroom.builder().name("103호").price(80000).studycafe(studycafeRepository.findById(1L).orElse(null)).build());
        studyroom.add(Studyroom.builder().name("101호").price(50000).studycafe(studycafeRepository.findById(2L).orElse(null)).build());
        studyroom.add(Studyroom.builder().name("102호").price(80000).studycafe(studycafeRepository.findById(2L).orElse(null)).build());
        studyroom.add(Studyroom.builder().name("101호").price(50000).studycafe(studycafeRepository.findById(3L).orElse(null)).build());
        studyroom.add(Studyroom.builder().name("102호").price(80000).studycafe(studycafeRepository.findById(3L).orElse(null)).build());
        studyroom.add(Studyroom.builder().name("101호").price(50000).studycafe(studycafeRepository.findById(4L).orElse(null)).build());
        studyroom.add(Studyroom.builder().name("102호").price(80000).studycafe(studycafeRepository.findById(4L).orElse(null)).build());
        studyroom.add(Studyroom.builder().name("101호").price(50000).studycafe(studycafeRepository.findById(5L).orElse(null)).build());
        studyroom.add(Studyroom.builder().name("102호").price(80000).studycafe(studycafeRepository.findById(5L).orElse(null)).build());
        studyroom.add(Studyroom.builder().name("101호").price(50000).studycafe(studycafeRepository.findById(6L).orElse(null)).build());
        studyroom.add(Studyroom.builder().name("102호").price(80000).studycafe(studycafeRepository.findById(6L).orElse(null)).build());
        studyroom.add(Studyroom.builder().name("101호").price(50000).studycafe(studycafeRepository.findById(7L).orElse(null)).build());
        studyroom.add(Studyroom.builder().name("102호").price(80000).studycafe(studycafeRepository.findById(7L).orElse(null)).build());
        studyroom.add(Studyroom.builder().name("101호").price(50000).studycafe(studycafeRepository.findById(8L).orElse(null)).build());
        studyroom.add(Studyroom.builder().name("102호").price(80000).studycafe(studycafeRepository.findById(8L).orElse(null)).build());
        studyroom.add(Studyroom.builder().name("101호").price(50000).studycafe(studycafeRepository.findById(9L).orElse(null)).build());
        studyroom.add(Studyroom.builder().name("102호").price(80000).studycafe(studycafeRepository.findById(9L).orElse(null)).build());

        ArrayList<StudyroomDetail> studyroomDetail = new ArrayList<>();

        studyroomDetail.add(StudyroomDetail.builder().maxNum(4).beam("Y").wboard("N").socket(2).studyroom(studyroom.get(0)).build());
        studyroomDetail.add(StudyroomDetail.builder().maxNum(5).beam("Y").wboard("N").socket(4).studyroom(studyroom.get(1)).build());
        studyroomDetail.add(StudyroomDetail.builder().maxNum(8).beam("Y").wboard("N").socket(8).studyroom(studyroom.get(2)).build());
        studyroomDetail.add(StudyroomDetail.builder().maxNum(5).beam("Y").wboard("N").socket(4).studyroom(studyroom.get(3)).build());
        studyroomDetail.add(StudyroomDetail.builder().maxNum(8).beam("Y").wboard("N").socket(8).studyroom(studyroom.get(4)).build());
        studyroomDetail.add(StudyroomDetail.builder().maxNum(5).beam("Y").wboard("N").socket(4).studyroom(studyroom.get(5)).build());
        studyroomDetail.add(StudyroomDetail.builder().maxNum(8).beam("Y").wboard("N").socket(8).studyroom(studyroom.get(6)).build());
        studyroomDetail.add(StudyroomDetail.builder().maxNum(5).beam("Y").wboard("N").socket(4).studyroom(studyroom.get(7)).build());
        studyroomDetail.add(StudyroomDetail.builder().maxNum(8).beam("Y").wboard("N").socket(8).studyroom(studyroom.get(8)).build());
        studyroomDetail.add(StudyroomDetail.builder().maxNum(5).beam("Y").wboard("N").socket(4).studyroom(studyroom.get(9)).build());
        studyroomDetail.add(StudyroomDetail.builder().maxNum(8).beam("Y").wboard("N").socket(8).studyroom(studyroom.get(10)).build());
        studyroomDetail.add(StudyroomDetail.builder().maxNum(5).beam("Y").wboard("N").socket(4).studyroom(studyroom.get(11)).build());
        studyroomDetail.add(StudyroomDetail.builder().maxNum(8).beam("Y").wboard("N").socket(8).studyroom(studyroom.get(12)).build());
        studyroomDetail.add(StudyroomDetail.builder().maxNum(5).beam("Y").wboard("N").socket(4).studyroom(studyroom.get(13)).build());
        studyroomDetail.add(StudyroomDetail.builder().maxNum(8).beam("Y").wboard("N").socket(8).studyroom(studyroom.get(14)).build());
        studyroomDetail.add(StudyroomDetail.builder().maxNum(5).beam("Y").wboard("N").socket(4).studyroom(studyroom.get(15)).build());
        studyroomDetail.add(StudyroomDetail.builder().maxNum(8).beam("Y").wboard("N").socket(8).studyroom(studyroom.get(16)).build());
        studyroomDetail.add(StudyroomDetail.builder().maxNum(5).beam("Y").wboard("N").socket(4).studyroom(studyroom.get(17)).build());
        studyroomDetail.add(StudyroomDetail.builder().maxNum(8).beam("Y").wboard("N").socket(8).studyroom(studyroom.get(18)).build());

        studyroomDetailRepository.saveAll(studyroomDetail);

        assertEquals(19, studyroomRepository.count());
        assertEquals(19, studyroomDetailRepository.count());
    }
}