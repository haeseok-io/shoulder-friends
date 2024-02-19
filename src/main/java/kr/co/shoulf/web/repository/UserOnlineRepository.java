package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Category;
import kr.co.shoulf.web.entity.UserOnline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOnlineRepository extends JpaRepository<UserOnline,Long> {
}
