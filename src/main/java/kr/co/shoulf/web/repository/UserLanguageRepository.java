package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.UserLanguage;
import kr.co.shoulf.web.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLanguageRepository extends JpaRepository<UserLanguage, Long> {
    List<UserLanguage> findByUsers(Users users);
}
