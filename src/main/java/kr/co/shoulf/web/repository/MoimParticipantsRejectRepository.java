package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Category;
import kr.co.shoulf.web.entity.MoimParticipants;
import kr.co.shoulf.web.entity.MoimParticipantsReject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoimParticipantsRejectRepository extends JpaRepository<MoimParticipantsReject, Long> {
    MoimParticipantsReject findByMoimParticipants_MoimParticipantsNo(Long participantsNo);

    void deleteByMoimParticipants(MoimParticipants p);
}
