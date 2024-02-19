package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.MemberSalaryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberSalaryDetailRepository extends JpaRepository<MemberSalaryDetail, Long> {
}
