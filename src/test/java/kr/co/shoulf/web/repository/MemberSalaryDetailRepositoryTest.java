package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.MemberSalary;
import kr.co.shoulf.web.entity.MemberSalaryDetail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MemberSalaryDetailRepositoryTest {
    @Autowired
    MemberSalaryRepository memberSalaryRepository;
    @Autowired
    MemberSalaryDetailRepository memberSalaryDetailRepository;

    @Test
    @DisplayName("급여 지급 내역 데이터 10개 추가")
    void testInsertMemberSalaryDetail() {
        // memberSalary 엔티티 1개 조회
        Optional<MemberSalary> memberSalary = memberSalaryRepository.findById(10L);
        // memberSalaryDetail 엔티티 1개 생성
        memberSalary.ifPresent(salary -> {
            MemberSalaryDetail memberSalaryDetail = MemberSalaryDetail.builder()
                    .paySal(350)
                    .payComm(50)
                    .payDate("2024-02-15")
                    .memberSalary(salary)
                    .build();
            // memberSalaryDetail 엔티티 저장
            memberSalaryDetailRepository.save(memberSalaryDetail);
        });

        // memberSalary 엔티티 9개 조회, 생성 및 저장
        for (int i = 2; i <= 10; i++) {
            // memberSalary 엔티티 조회
            memberSalary = memberSalaryRepository.findById((long)(i));

            // memberSalaryDetail 엔티티 생성
            memberSalary.ifPresent(salary -> {
                MemberSalaryDetail memberSalaryDetail = MemberSalaryDetail.builder()
                        .paySal(300)
                        .payComm(null)
                        .payDate("2024-02-15")
                        .memberSalary(salary)
                        .build();
                // memberSalaryDetail 엔티티 저장
                memberSalaryDetailRepository.save(memberSalaryDetail);
            });
        }
        // 저장된 memberSalaryDetail 엔티티 갯수 확인
        Long count = memberSalaryDetailRepository.count();
        assertEquals(10, count);
    }
}