package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Meeting;
import kr.co.shoulf.web.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<Platform,Integer> {
}
