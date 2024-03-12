package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
    List<Users> findTop12ByOrderByUserNoDesc();

    boolean existsByEmail(String email);

    Users findByNickname(String nickName);
}
