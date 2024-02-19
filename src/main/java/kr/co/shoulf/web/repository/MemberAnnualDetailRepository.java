package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.MemberAnnualDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAnnualDetailRepository extends JpaRepository<MemberAnnualDetail, Long> {
}
