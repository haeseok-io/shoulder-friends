package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("직원 10명 추가")
    void testInsertMember() {
        //ArrayList<Member> test = new ArrayList<>();
        //
        //test.add(Member.builder().id("admin").pw("1111").name("뽀로로").post("42250").addr("대구광역시 수성구").phone("1011111111").hiredate("2024-01-01").role("ROLE_ADMIN").build());
        //test.add(Member.builder().id("bbb").pw("2222").name("크롱").post("44069").addr("고양시 일산동구").phone("1022222222").hiredate("2024-01-01").role("ROLE_EMP").build());
        //test.add(Member.builder().id("ccc").pw("3333").name("루피").post("57630").addr("서울시 송파구").phone("1033333333").hiredate("2024-01-01").role("ROLE_EMP").build());
        //test.add(Member.builder().id("ddd").pw("4444").name("포비").post("20550").addr("서울시 중랑구").phone("1044444444").hiredate("2024-01-01").role("ROLE_EMP").build());
        //test.add(Member.builder().id("eee").pw("5555").name("패티").post("17094").addr("용인시 기흥구").phone("1055555555").hiredate("2024-01-01").role("ROLE_EMP").build());
        //test.add(Member.builder().id("fff").pw("6666").name("짱구").post("51694").addr("창원시 진해구").phone("1066666666").hiredate("2024-01-01").role("ROLE_EMP").build());
        //test.add(Member.builder().id("ggg").pw("7777").name("유리").post("48104").addr("부산시 해운대구").phone("1077777777").hiredate("2024-01-01").role("ROLE_EMP").build());
        //test.add(Member.builder().id("hhh").pw("8888").name("훈이").post("42669").addr("대구시 달서구").phone("1088888888").hiredate("2024-01-01").role("ROLE_EMP").build());
        //test.add(Member.builder().id("iii").pw("9999").name("맹구").post("49700").addr("서울시 광진구").phone("1099999999").hiredate("2024-01-01").role("ROLE_EMP").build());
        //test.add(Member.builder().id("jjj").pw("1010").name("철수").post("60880").addr("서울시 강남구").phone("1010101010").hiredate("2024-01-01").role("ROLE_EMP").build());
        //
        //memberRepository.saveAll(test);
    }
}