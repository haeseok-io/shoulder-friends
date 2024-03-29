package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.PositionDetail;
import kr.co.shoulf.web.entity.UserJob;
import kr.co.shoulf.web.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJobRepository extends JpaRepository<UserJob,Long> {
    UserJob findByUsers(Users users);
    List<UserJob> findByPositionDetail(PositionDetail positionDetail);
}
