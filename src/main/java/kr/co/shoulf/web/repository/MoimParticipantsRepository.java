package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.MoimHeadcount;
import kr.co.shoulf.web.entity.MoimParticipants;
import kr.co.shoulf.web.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoimParticipantsRepository extends JpaRepository<MoimParticipants, Long> {
    Integer countByMoimHeadcountAndStatus(MoimHeadcount moimHeadcount, Integer status);
    Integer countByUsersAndStatus(Users users, Integer status);
}
