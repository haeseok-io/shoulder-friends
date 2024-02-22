package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Moim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoimRepository extends JpaRepository<Moim,Long> {
    List<Moim> findTop2ByOrderByMoimNoDesc();
}
