package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Moim;
import kr.co.shoulf.web.entity.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MoimRepositoryTest {
    @Autowired
    private MoimRepository moimRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("모임 테스트 데이터 추가")
    void testInsertMoim() {
        List<Users> userList = userRepository.findAll();
        List<Moim> moimList = new ArrayList<>();

        moimList.add(Moim.builder().type("proejct").subject("프로젝트1").shortDesc("애견앱 개발").status(1).hits(1).users(userList.get(0)).build());
        moimList.add(Moim.builder().type("proejct").subject("프로젝트2").shortDesc("데이팅앱 개발").status(1).hits(1).users(userList.get(1)).build());
        moimList.add(Moim.builder().type("study").subject("스터디1").shortDesc("스프링스터디").status(1).hits(1).users(userList.get(2)).build());
        moimList.add(Moim.builder().type("study").subject("스터디2").shortDesc("파이썬스터디").status(1).hits(1).users(userList.get(3)).build());
        moimList.add(Moim.builder().type("proejct").subject("프로젝트3").shortDesc("쇼핑몰 개발").status(1).hits(1).users(userList.get(4)).build());
        moimList.add(Moim.builder().type("proejct").subject("프로젝트4").shortDesc("헬스앱 개발").status(1).hits(1).users(userList.get(5)).build());
        moimList.add(Moim.builder().type("study").subject("스터디3").shortDesc("코테 스터디").status(1).hits(1).users(userList.get(6)).build());
        moimList.add(Moim.builder().type("study").subject("스터디4").shortDesc("SQL 스터디").status(1).hits(1).users(userList.get(7)).build());
        moimList.add(Moim.builder().type("proejct").subject("프로젝트5").shortDesc("인공지능 활용앱 개발").status(1).hits(1).users(userList.get(8)).build());
        moimList.add(Moim.builder().type("proejct").subject("프로젝트6").shortDesc("소셜앱 개발").status(1).hits(1).users(userList.get(9)).build());

        moimList.forEach(moim -> moimRepository.save(moim));
    }
}