package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.MemberCommute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberCommuteRepository extends JpaRepository<MemberCommute, Long> {
}
