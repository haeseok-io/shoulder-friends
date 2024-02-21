package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Studyroom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudyroomRepositoryTest {
    @Autowired
    StudycafeRepository studycafeRepository;
    @Autowired
    StudyroomRepository studyroomRepository;

    @Test
    @DisplayName("스터디룸 데이터 19개 추가")
    void testInsertStudyroom () {
        ArrayList<Studyroom> test = new ArrayList<>();

        test.add(Studyroom.builder().name("101호").price(40000).studycafe(studycafeRepository.findById(1L).get()).build());
        test.add(Studyroom.builder().name("102호").price(50000).studycafe(studycafeRepository.findById(1L).get()).build());
        test.add(Studyroom.builder().name("103호").price(80000).studycafe(studycafeRepository.findById(1L).get()).build());
        test.add(Studyroom.builder().name("101호").price(50000).studycafe(studycafeRepository.findById(2L).get()).build());
        test.add(Studyroom.builder().name("102호").price(80000).studycafe(studycafeRepository.findById(2L).get()).build());
        test.add(Studyroom.builder().name("101호").price(50000).studycafe(studycafeRepository.findById(3L).get()).build());
        test.add(Studyroom.builder().name("102호").price(80000).studycafe(studycafeRepository.findById(3L).get()).build());
        test.add(Studyroom.builder().name("101호").price(50000).studycafe(studycafeRepository.findById(4L).get()).build());
        test.add(Studyroom.builder().name("102호").price(80000).studycafe(studycafeRepository.findById(4L).get()).build());
        test.add(Studyroom.builder().name("101호").price(50000).studycafe(studycafeRepository.findById(5L).get()).build());
        test.add(Studyroom.builder().name("102호").price(80000).studycafe(studycafeRepository.findById(5L).get()).build());
        test.add(Studyroom.builder().name("101호").price(50000).studycafe(studycafeRepository.findById(6L).get()).build());
        test.add(Studyroom.builder().name("102호").price(80000).studycafe(studycafeRepository.findById(6L).get()).build());
        test.add(Studyroom.builder().name("101호").price(50000).studycafe(studycafeRepository.findById(7L).get()).build());
        test.add(Studyroom.builder().name("102호").price(80000).studycafe(studycafeRepository.findById(7L).get()).build());
        test.add(Studyroom.builder().name("101호").price(50000).studycafe(studycafeRepository.findById(8L).get()).build());
        test.add(Studyroom.builder().name("102호").price(80000).studycafe(studycafeRepository.findById(8L).get()).build());
        test.add(Studyroom.builder().name("101호").price(50000).studycafe(studycafeRepository.findById(9L).get()).build());
        test.add(Studyroom.builder().name("102호").price(80000).studycafe(studycafeRepository.findById(9L).get()).build());

        studyroomRepository.saveAll(test);

        assertEquals(19, studyroomRepository.count());
    }
}