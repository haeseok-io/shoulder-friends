package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Category;
import kr.co.shoulf.web.entity.Online;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnlineRepository extends JpaRepository<Online,Long> {
}
