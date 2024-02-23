package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Category;
import kr.co.shoulf.web.entity.UserOnline;
import kr.co.shoulf.web.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOnlineRepository extends JpaRepository<UserOnline,Long> {
    UserOnline findByUsers(Users users);
}
