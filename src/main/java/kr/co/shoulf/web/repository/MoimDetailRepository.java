package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.MoimDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoimDetailRepository extends JpaRepository<MoimDetail,Long> {
}
