package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Category;
import kr.co.shoulf.web.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position,Long> {
}
