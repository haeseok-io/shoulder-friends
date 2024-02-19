package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Users;
import org.apache.catalina.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("임시 회원 10명 추가")
    void testInsertUser() {
        ArrayList<Users> test = new ArrayList<>();

        test.add(Users.builder().email("aaa@naver.com").pass("1111").nickname("홍길동").role("ROLE_USER").build());
        test.add(Users.builder().email("bbb@naver.com").pass("2222").nickname("김광현").role("ROLE_USER").build());
        test.add(Users.builder().email("ccc@naver.com").pass("3333").nickname("안정환").role("ROLE_USER").build());
        test.add(Users.builder().email("ddd@naver.com").pass("4444").nickname("손흥민").role("ROLE_USER").build());
        test.add(Users.builder().email("eee@naver.com").pass("5555").nickname("이영표").role("ROLE_USER").build());
        test.add(Users.builder().email("fff@naver.com").pass("6666").nickname("박지성").role("ROLE_USER").build());
        test.add(Users.builder().email("ggg@naver.com").pass("7777").nickname("조규성").role("ROLE_USER").build());
        test.add(Users.builder().email("hhh@naver.com").pass("8888").nickname("이강인").role("ROLE_USER").build());
        test.add(Users.builder().email("iii@naver.com").pass("9999").nickname("황인범").role("ROLE_USER").build());
        test.add(Users.builder().email("jjj@naver.com").pass("1010").nickname("김민재").role("ROLE_USER").build());

        test.forEach(users -> userRepository.save(users));
    }

}