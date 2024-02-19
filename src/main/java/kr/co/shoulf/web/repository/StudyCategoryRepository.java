package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.PositionDetail;
import kr.co.shoulf.web.entity.StudyCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyCategoryRepository extends JpaRepository<StudyCategory,Long> {
}
