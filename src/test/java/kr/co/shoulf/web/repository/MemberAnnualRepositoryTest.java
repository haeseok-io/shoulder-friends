package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Member;
import kr.co.shoulf.web.entity.MemberAnnual;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberAnnualRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberAnnualRepository memberAnnualRepository;

    @Test
    @DisplayName("직원 연차 데이터 10개 추가")
    void testInsertMemberAnnual() {
        // memberAnnual 데이터 10개 조회, 생성, 저장
        for (int i = 0; i < 10; i++) {
            Optional<Member> optionalMember = memberRepository.findById((long) (i + 1));
            if (optionalMember.isPresent()) {
                Member member = optionalMember.get();

                MemberAnnual memberAnnual = MemberAnnual.builder()
                        .type(1)
                        .num(7)
                        .reason("기본 지급")
                        .issueDate("2024-01-01")
                        .member(member)
                        .build();

                memberAnnualRepository.save(memberAnnual);
            }
        }
            Long count = memberAnnualRepository.count();
            assertEquals(10, count);
    }
}