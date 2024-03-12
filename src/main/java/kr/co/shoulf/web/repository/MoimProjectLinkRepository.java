package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Moim;
import kr.co.shoulf.web.entity.MoimProjectLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoimProjectLinkRepository extends JpaRepository<MoimProjectLink,Long> {
    List<MoimProjectLink> findByMoim(Moim moim);
}
