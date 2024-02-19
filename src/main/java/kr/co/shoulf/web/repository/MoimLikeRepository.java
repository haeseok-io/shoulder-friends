package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Meeting;
import kr.co.shoulf.web.entity.MoimLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoimLikeRepository extends JpaRepository<MoimLike,Long> {
}
