package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.MemberAnnualDetail;
import kr.co.shoulf.web.entity.MemberAnnualRejected;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MemberAnnualRejectedRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberAnnualDetailRepository memberAnnualDetailRepository;
    @Autowired
    MemberAnnualRejectedRepository memberAnnualRejectedRepository;

    @Test
    @DisplayName("연차 반려 사유 데이터 1개 추가")
    void testInsertMemberAnnualRejectedRepository() {
        Optional<MemberAnnualDetail> optionalMemberAnnualDetail = memberAnnualDetailRepository.findById(4L);

        MemberAnnualRejected memberAnnualRejected = null;
        if (optionalMemberAnnualDetail.isPresent()) {
            memberAnnualRejected = MemberAnnualRejected.builder()
                    .content("너무 길어서")
                    .member(memberRepository.findById(1L).orElse(null))
                    .memberAnnualDetail(optionalMemberAnnualDetail.get())
                    .build();

            assert memberAnnualRejected != null;
            memberAnnualRejectedRepository.save(memberAnnualRejected);
        }

        assertEquals(1, memberAnnualRejectedRepository.count());
    }
}