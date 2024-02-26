package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);

    boolean existsByEmail(String email);
}
