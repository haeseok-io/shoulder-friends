package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.PositionDetail;
import kr.co.shoulf.web.entity.StudyCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StudyCategoryRepositoryTest {
    @Autowired
    private StudyCategoryRepository studyCategoryRepository;

    @Test
    @DisplayName("스터디 카테고리 추가")
    void testInsertStudyCategory() {
        ArrayList<StudyCategory> test = new ArrayList<>();

        test.add(StudyCategory.builder().studyCategoryName("기획&PO").build());
        test.add(StudyCategory.builder().studyCategoryName("디자인&UX").build());
        test.add(StudyCategory.builder().studyCategoryName("프론트엔드개발").build());
        test.add(StudyCategory.builder().studyCategoryName("벡엔드 개발").build());
        test.add(StudyCategory.builder().studyCategoryName("사업&리서치").build());
        test.add(StudyCategory.builder().studyCategoryName("어학&학업").build());
        test.add(StudyCategory.builder().studyCategoryName("취업&취직").build());
        test.add(StudyCategory.builder().studyCategoryName("시험&고시").build());
        test.add(StudyCategory.builder().studyCategoryName("예술&공예").build());
        test.add(StudyCategory.builder().studyCategoryName("요리&식음관련").build());
        test.add(StudyCategory.builder().studyCategoryName("운동&헬스").build());
        test.add(StudyCategory.builder().studyCategoryName("사진&영상").build());
        test.add(StudyCategory.builder().studyCategoryName("기타").build());

        test.forEach(studyCategory -> studyCategoryRepository.save(studyCategory));
    }

}