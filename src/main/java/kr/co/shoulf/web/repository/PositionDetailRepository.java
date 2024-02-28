package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.PositionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionDetailRepository extends JpaRepository<PositionDetail, Integer> {
    List<PositionDetail> findByPosition_PositionNo(Integer positionNo);

}
