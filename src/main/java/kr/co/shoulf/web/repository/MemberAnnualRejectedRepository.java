package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.MemberAnnualRejected;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAnnualRejectedRepository extends JpaRepository<MemberAnnualRejected, Long> {
}
