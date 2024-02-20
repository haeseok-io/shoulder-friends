package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.MemberAnnual;
import kr.co.shoulf.web.entity.MemberAnnualDetail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MemberAnnualDetailRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberAnnualRepository memberAnnualRepository;
    @Autowired
    MemberAnnualDetailRepository memberAnnualDetailRepository;

    @Test
    @DisplayName("연차 사용 내역 데이터 3개 추가")
    void testInsertMemberAnnualDetail() {
        // 연차 고유 번호 조회
        Optional<MemberAnnual> optionalMemberAnnual7 = memberAnnualRepository.findById(7L);
        Optional<MemberAnnual> optionalMemberAnnual2 = memberAnnualRepository.findById(2L);
        Optional<MemberAnnual> optionalMemberAnnual6 = memberAnnualRepository.findById(6L);

        // 엔터티 생성 및 저장
        if (optionalMemberAnnual7.isPresent()) {
            // 엔터티 생성
            MemberAnnualDetail memberAnnualDetail = MemberAnnualDetail.builder()
                    .useCate(1)
                    .startDate("2024-01-02")
                    .endDate("2024-01-07")
                    .useNum(1)
                    .status(2)
                    .member(optionalMemberAnnual7.get().getMember()) // 신청자
                    .approvalMember(memberRepository.findById(1L).orElse(null)) // 결정자
                    .memberAnnual(optionalMemberAnnual7.get())
                    .build();
            // 엔터티 저장
            memberAnnualDetailRepository.save(memberAnnualDetail);
        }
        if (optionalMemberAnnual2.isPresent()) {
            // 엔터티 생성
            MemberAnnualDetail memberAnnualDetail = MemberAnnualDetail.builder()
                    .useCate(1)
                    .startDate("2024-01-02")
                    .endDate("2024-01-07")
                    .useNum(6)
                    .status(3)
                    .member(optionalMemberAnnual2.get().getMember()) // 신청자
                    .approvalMember(memberRepository.findById(1L).orElse(null)) // 결정자
                    .memberAnnual(optionalMemberAnnual2.get())
                    .build();
            // 엔터티 저장
            memberAnnualDetailRepository.save(memberAnnualDetail);
        }
        if (optionalMemberAnnual6.isPresent()) {
            // 엔터티 생성
            MemberAnnualDetail memberAnnualDetail = MemberAnnualDetail.builder()
                    .useCate(1)
                    .startDate("2024-01-03")
                    .endDate("2024-01-07")
                    .useNum(5)
                    .status(1)
                    .member(optionalMemberAnnual6.get().getMember()) // 신청자
                    .approvalMember(memberRepository.findById(0L).orElse(null)) // 결정자
                    .memberAnnual(optionalMemberAnnual6.get())
                    .build();

            // 엔터티 저장
            memberAnnualDetailRepository.save(memberAnnualDetail);
        }
        // 엔터티 갯수 확인
        Long count = memberAnnualDetailRepository.count();
        assertEquals(3, count);
    }
}