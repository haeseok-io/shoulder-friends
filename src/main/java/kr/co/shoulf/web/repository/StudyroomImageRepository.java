package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Studyroom;
import kr.co.shoulf.web.entity.StudyroomImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyroomImageRepository extends JpaRepository<StudyroomImage, Long> {
    //스터디룸 번호로 이미지 목록
    List<StudyroomImage> findByStudyroom(Studyroom studyroom);
}
