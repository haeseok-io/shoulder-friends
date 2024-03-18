package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChecklistRepository extends JpaRepository<Checklist, Long> {
    //모임번호로 체크리스트 찾기
    List<Checklist> findByMoim_MoimNo(Long moimNo);
    //모임번호랑 상태로 체크리스트 찾기
    List<Checklist> findByMoim_MoimNoAndStatus(Long moimNo, int status);
}
