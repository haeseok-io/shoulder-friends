package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Studycafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudycafeRepository extends JpaRepository<Studycafe, Long> {
}
