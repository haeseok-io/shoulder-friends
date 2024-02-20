package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Category;
import kr.co.shoulf.web.entity.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("카테고리 추가")
    void testInsertCategory() {
        ArrayList<Category> test = new ArrayList<>();

        test.add(Category.builder().categoryName("O2O").build());
        test.add(Category.builder().categoryName("공유서비스").build());
        test.add(Category.builder().categoryName("데이트/결혼").build());
        test.add(Category.builder().categoryName("여행").build());
        test.add(Category.builder().categoryName("소셜네트워크").build());
        test.add(Category.builder().categoryName("뷰티/패션").build());
        test.add(Category.builder().categoryName("이커머스").build());
        test.add(Category.builder().categoryName("엔터테인먼트").build());
        test.add(Category.builder().categoryName("게임").build());
        test.add(Category.builder().categoryName("헬스/스포츠").build());
        test.add(Category.builder().categoryName("뉴스/정보").build());
        test.add(Category.builder().categoryName("유틸").build());
        test.add(Category.builder().categoryName("금융").build());
        test.add(Category.builder().categoryName("부동산/인테리어").build());
        test.add(Category.builder().categoryName("종교").build());
        test.add(Category.builder().categoryName("교육").build());
        test.add(Category.builder().categoryName("의료/병원").build());
        test.add(Category.builder().categoryName("모빌리티(교통)").build());
        test.add(Category.builder().categoryName("육아/출산").build());
        test.add(Category.builder().categoryName("우주/외계인").build());

        test.forEach(category -> categoryRepository.save(category));
    }


}