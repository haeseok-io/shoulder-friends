package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Member;
import kr.co.shoulf.web.entity.MemberCommute;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MemberCommuteRepositoryTest{
    @Autowired
    MemberCommuteRepository memberCommuteRepository;
    @Autowired
    MemberRepository memberRepository;
    
    @Test
    @DisplayName("출퇴근목록 데이터 10개 추가")
    void testInsertMemberCommute() {
        //// 10개의 MemberCommute 엔티티 생성 및 저장
        //for (int i = 0; i < 10; i++) {
        //    // Member 엔티티 조회
        //    Optional<Member> optionalMember = memberRepository.findById((long) (i + 1));
        //    if (optionalMember.isPresent()) {
        //        Member member = optionalMember.get();
        //
        //        // MemberCommute 엔티티 생성
        //        MemberCommute memberCommute = MemberCommute.builder()
        //                .inTime(new Date()) // 현재 시간으로 설정
        //                .outTime(new Date()) // 현재 시간으로 설정
        //                .status("정상")
        //                .member(member) // Member 엔티티 설정
        //                .build();
        //
        //        // MemberCommute 엔티티 저장
        //        memberCommuteRepository.save(memberCommute);
        //    }
        //}
        //
        //// 저장된 MemberCommute 엔티티 개수 확인
        //Long count = memberCommuteRepository.count();
        //assertEquals(10, count);
    }
}