package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Category;
import kr.co.shoulf.web.entity.UserJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJobRepository extends JpaRepository<UserJob,Long> {
}
