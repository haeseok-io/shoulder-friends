package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.UserInterestCategory;
import kr.co.shoulf.web.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInterestCategoryRepository extends JpaRepository<UserInterestCategory,Long> {
    List<UserInterestCategory> findByUsers(Users users);
}
