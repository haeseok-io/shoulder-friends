package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findById(String id);
    boolean existsById(String id);
}
