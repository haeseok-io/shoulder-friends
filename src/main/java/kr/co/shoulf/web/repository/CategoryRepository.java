package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
