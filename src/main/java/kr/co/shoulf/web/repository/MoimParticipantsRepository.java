package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Moim;
import kr.co.shoulf.web.entity.MoimHeadcount;
import kr.co.shoulf.web.entity.MoimParticipants;
import kr.co.shoulf.web.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoimParticipantsRepository extends JpaRepository<MoimParticipants, Long> {
    List<MoimParticipants> findByMoimHeadcount(MoimHeadcount moimHeadcount);
    List<MoimParticipants> findByMoimHeadcountAndStatus(MoimHeadcount moimHeadcount, Integer status);
    List<MoimParticipants> findByUsersAndStatus(Users users, Integer status);

    void deleteByMoimHeadcount(MoimHeadcount moimHeadcount);
}
