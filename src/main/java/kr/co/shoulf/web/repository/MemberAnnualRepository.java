package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.MemberAnnual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberAnnualRepository extends JpaRepository<MemberAnnual, Long> {
    List<MemberAnnual> findByMember_MemberNo(Long memberNo);
    MemberAnnual findByAnnualNo(Long annualNo);
}
