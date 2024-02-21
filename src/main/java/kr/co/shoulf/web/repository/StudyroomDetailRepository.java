package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.StudyroomDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyroomDetailRepository extends JpaRepository<StudyroomDetail, Long> {
}