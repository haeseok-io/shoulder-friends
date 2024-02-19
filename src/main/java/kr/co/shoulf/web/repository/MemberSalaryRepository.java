package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.MemberSalary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberSalaryRepository extends JpaRepository<MemberSalary, Long> {
}
