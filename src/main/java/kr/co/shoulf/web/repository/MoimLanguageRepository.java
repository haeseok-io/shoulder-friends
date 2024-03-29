package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Meeting;
import kr.co.shoulf.web.entity.Moim;
import kr.co.shoulf.web.entity.MoimLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoimLanguageRepository extends JpaRepository<MoimLanguage,Long> {
    List<MoimLanguage> findByMoim(Moim moim);
}
