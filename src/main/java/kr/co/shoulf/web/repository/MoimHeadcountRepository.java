package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Category;
import kr.co.shoulf.web.entity.Moim;
import kr.co.shoulf.web.entity.MoimHeadcount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoimHeadcountRepository extends JpaRepository<MoimHeadcount,Long> {
    List<MoimHeadcount> findByMoim(Moim moim);
}
