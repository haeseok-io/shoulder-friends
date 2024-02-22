package kr.co.shoulf.web.repository;

import com.sun.java.accessibility.util.EventID;
import kr.co.shoulf.web.entity.Studycafe;
import kr.co.shoulf.web.entity.Studyroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@Repository
public interface StudyroomRepository extends JpaRepository<Studyroom, Long> {
    List<Studyroom> findByStudycafe_StudycafeNo(Long studyCafeNo);
}
