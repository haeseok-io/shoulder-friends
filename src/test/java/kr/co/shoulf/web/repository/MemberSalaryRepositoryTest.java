package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Member;
import kr.co.shoulf.web.entity.MemberSalary;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberSalaryRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberSalaryRepository memberSalaryRepository;

    @Test
    @DisplayName("직원 급여 데이터 10개 추가")
    void testInsertMemberSalary () {
        // MemberSalary 엔티티 10개 생성 및 저장
        for (int i = 0; i < 10; i++) {
            // Member 엔티티 조회
            Optional<Member> optionalMember = memberRepository.findById((long) (i + 1));
            if (optionalMember.isPresent()) {
                Member member = optionalMember.get();

                // MemberSalary 엔티티 생성
                MemberSalary memberSalary = MemberSalary.builder()
                        .sal(1000)
                        .member(member)
                        .build();

                // MemberSalary 엔티티 저장
                memberSalaryRepository.save(memberSalary);
            }
        }
        // 저장된 MemberSalary 엔티티 갯수 확인
        Long count = memberSalaryRepository.count();
        assertEquals(10, count);
    }
}