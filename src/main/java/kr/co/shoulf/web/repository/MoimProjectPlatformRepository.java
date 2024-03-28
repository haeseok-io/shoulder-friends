package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Moim;
import kr.co.shoulf.web.entity.MoimProjectPlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoimProjectPlatformRepository extends JpaRepository<MoimProjectPlatform,Long> {
    List<MoimProjectPlatform> findByMoim(Moim moim);

    void deleteByMoim(Moim moim);
}
