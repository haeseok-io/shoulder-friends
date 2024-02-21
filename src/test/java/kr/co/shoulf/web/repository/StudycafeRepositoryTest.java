package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Studycafe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StudycafeRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    StudycafeRepository studycafeRepository;

    @Test
    @DisplayName("스터디카페 데이터 9개 추가")
    void testInsertStudycafe() {
        ArrayList<Studycafe> test = new ArrayList<>();

        test.add(Studycafe.builder().name("어깨동무 스터디 카페 종로점").shortDesc("스터디 카페 종로점입니다.").post("3134").addr("서울 종로구 율곡로10길 105").mainImg("https://kr.acrofan.com/news_images/2021/04/16/mark/2021041671633.jpg").x(37.5729361).y(126.9922303).member(memberRepository.findById(2L).orElse(null)).build());
        test.add(Studycafe.builder().name("어깨동무 스터디 카페 강남점").shortDesc("스터디 카페 강남점입니다.").post("6130").addr("서울 강남구 테헤란로7길 21").mainImg("https://kr.acrofan.com/news_images/2021/04/16/mark/2021041671633.jpg").x(37.5009602).y(127.0299804).member(memberRepository.findById(3L).orElse(null)).build());
        test.add(Studycafe.builder().name("어깨동무 스터디 카페 서대문구점").shortDesc("스터디 카페 서대문구점입니다.").post("3718").addr("서울 서대문구 연희로 248").mainImg("https://kr.acrofan.com/news_images/2021/04/16/mark/2021041671633.jpg").x(37.5792596).y(126.9364938).member(memberRepository.findById(4L).orElse(null)).build());
        test.add(Studycafe.builder().name("어깨동무 스터디 카페 용인점").shortDesc("스터디 카페 용인점입니다.").post("17019").addr("경기 용인시 처인구 중부대로 1199").mainImg("https://kr.acrofan.com/news_images/2021/04/16/mark/2021041671633.jpg").x(37.2408414).y(127.1775117).member(memberRepository.findById(5L).orElse(null)).build());
        test.add(Studycafe.builder().name("어깨동무 스터디 카페 인천점").shortDesc("스터디 카페 인천점입니다.").post("21554").addr("인천 남동구 정각로 29").mainImg("https://kr.acrofan.com/news_images/2021/04/16/mark/2021041671633.jpg").x(37.456255).y(126.705206).member(memberRepository.findById(6L).orElse(null)).build());
        test.add(Studycafe.builder().name("어깨동무 스터디 카페 대구점").shortDesc("스터디 카페 대구점입니다.").post("41939").addr("대구 중구 공평로10길 25").mainImg("https://kr.acrofan.com/news_images/2021/04/16/mark/2021041671633.jpg").x(35.8686883).y(128.601256).member(memberRepository.findById(7L).orElse(null)).build());
        test.add(Studycafe.builder().name("어깨동무 스터디 카페 부산점").shortDesc("스터디 카페 부산점입니다.").post("47545").addr("부산 연제구 중앙대로 1001").mainImg("https://kr.acrofan.com/news_images/2021/04/16/mark/2021041671633.jpg").x(35.1800626).y(129.0745963).member(memberRepository.findById(8L).orElse(null)).build());
        test.add(Studycafe.builder().name("어깨동무 스터디 카페 광주점").shortDesc("스터디 카페 광주점입니다.").post("61945").addr("광주 서구 내방로 111").mainImg("https://kr.acrofan.com/news_images/2021/04/16/mark/2021041671633.jpg").x(25.1597705).y(126.8515218).member(memberRepository.findById(9L).orElse(null)).build());
        test.add(Studycafe.builder().name("어깨동무 스터디 카페 대전점").shortDesc("스터디 카페 대전점입니다.").post("35242").addr("대전 서구 둔산로 100").mainImg("https://kr.acrofan.com/news_images/2021/04/16/mark/2021041671633.jpg").x(36.3501707).y(127.3848389).member(memberRepository.findById(10L).orElse(null)).build());

        studycafeRepository.saveAll(test);

        assertEquals(9, studycafeRepository.count());
    }
}