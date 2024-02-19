package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.UserDetail;
import kr.co.shoulf.web.entity.UserPortfolio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserPortfolioRepositoryTest {
    @Autowired
    private UserPortfolioRepository userPortfolioRepository;
    @Autowired
    private UserDetailRepository userDetailRepository;

    @Test
    @DisplayName("회원 포트폴리오 테스트 데이터 추가")
    void testInsertUserPortfolio() {
        UserDetail userDetail1 = userDetailRepository.findById(1L).get();
        UserDetail userDetail4 = userDetailRepository.findById(4L).get();
        UserDetail userDetail7 = userDetailRepository.findById(7L).get();

        userPortfolioRepository.save(UserPortfolio.builder().url("https://drive.google.com/aaa").userDetail(userDetail1).build());
        userPortfolioRepository.save(UserPortfolio.builder().url("https://drive.google.com/ddd").userDetail(userDetail4).build());
        userPortfolioRepository.save(UserPortfolio.builder().url("https://drive.google.com/ggg").userDetail(userDetail7).build());
    }
}