package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.StudyroomImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyroomImageRepository extends JpaRepository<StudyroomImage, Long> {
}
