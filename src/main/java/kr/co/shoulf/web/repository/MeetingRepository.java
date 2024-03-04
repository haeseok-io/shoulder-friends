package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting,Long> {
    List<Meeting> findByMoim_MoimNo(Long moimNo);
}
