package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.StudycafeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudycafeDetailRepository extends JpaRepository<StudycafeDetail, Long> {
}
