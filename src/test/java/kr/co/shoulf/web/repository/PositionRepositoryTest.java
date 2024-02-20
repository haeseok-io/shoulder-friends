package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Category;
import kr.co.shoulf.web.entity.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PositionRepositoryTest {
    @Autowired
    private PositionRepository positionRepository;

    @Test
    @DisplayName("직무 대분류 추가")
    void testInsertPosition() {
        ArrayList<Position> test = new ArrayList<>();

        test.add(Position.builder().bigName("기획").build());
        test.add(Position.builder().bigName("디자인").build());
        test.add(Position.builder().bigName("프론트엔드개발").build());
        test.add(Position.builder().bigName("백엔드개발").build());
        test.add(Position.builder().bigName("사업").build());
        test.add(Position.builder().bigName("기타").build());

        test.forEach(position -> positionRepository.save(position));
    }

}