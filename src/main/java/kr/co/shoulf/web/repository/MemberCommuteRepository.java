package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.MemberCommute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MemberCommuteRepository extends JpaRepository<MemberCommute, Long> {
    MemberCommute findByMember_MemberNoAndDate(Long memberNo, Date selectedDate);
    List<MemberCommute> findByDate(Date selectedDate);
}
