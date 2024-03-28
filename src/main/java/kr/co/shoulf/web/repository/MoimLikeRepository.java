package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Moim;
import kr.co.shoulf.web.entity.MoimLike;
import kr.co.shoulf.web.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoimLikeRepository extends JpaRepository<MoimLike,Long> {
    List<MoimLike> findByMoim(Moim moim);
    List<MoimLike> findByUsers(Users users);

    MoimLike findByMoimAndUsers_UserNo(Moim moim, Long userNo);

    void deleteByMoimAndUsers_UserNo(Moim moim, Long userNo);
}
