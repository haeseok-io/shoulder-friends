package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Category;
import kr.co.shoulf.web.entity.MoimParticipants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoimParticipantsRepository extends JpaRepository<MoimParticipants,Long> {
}
