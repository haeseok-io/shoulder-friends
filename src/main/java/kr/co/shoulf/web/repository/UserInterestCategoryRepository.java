package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Category;
import kr.co.shoulf.web.entity.UserInterestCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInterestCategoryRepository extends JpaRepository<UserInterestCategory,Long> {
}
