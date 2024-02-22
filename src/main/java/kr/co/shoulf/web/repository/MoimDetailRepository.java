package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.MoimDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoimDetailRepository extends JpaRepository<MoimDetail,Long> {

    @Query(value = "SELECT * FROM moim_detail  m order by m.moim_no desc limit :limit", nativeQuery = true)
    List<MoimDetail> readNewMoim(@Param("limit")int limit);

    List<MoimDetail> findTop2ByOrderByMoimNoDesc();
}
