package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Member;
import kr.co.shoulf.web.entity.MemberAnnualDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberAnnualDetailRepository extends JpaRepository<MemberAnnualDetail, Long> {
    List<MemberAnnualDetail> findByMemberAnnual_MemberAndMember_MemberNo(Member member, Long memberNo);
    MemberAnnualDetail findByMember_MemberNo(Long memberNo);
    MemberAnnualDetail findByAnnualDetailNoAndMember_MemberNo(Long annualDetailNo, Long memberNo);


}
