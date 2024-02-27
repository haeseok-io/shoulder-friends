package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Studycafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudycafeRepository extends JpaRepository<Studycafe, Long> {
    List<Studycafe> findByMember_MemberNo(Long memberNo);
}
