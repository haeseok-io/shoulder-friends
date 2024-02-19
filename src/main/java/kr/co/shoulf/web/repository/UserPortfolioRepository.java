package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.UserPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPortfolioRepository extends JpaRepository<UserPortfolio, Long> {
}
