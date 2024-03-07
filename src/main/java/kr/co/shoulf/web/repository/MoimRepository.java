package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Moim;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoimRepository extends JpaRepository<Moim,Long> {
    List<Moim> findTop2ByOrderByMoimNoDesc();
    List<Moim> findTop8ByOrderByHitsDesc();

    @Query("Select m From Moim m " +
            "Left Join fetch m.moimDetail md " +
            "Left Join fetch m.moimHeadcountList mh " +
            "Left Join fetch mh.positionDetail pd " +
            "Left Join pd.position p " +
            "Left Join m.moimLanguageList ml " +
            "Left Join md.category c " +
            "Left Join md.studyCategory sc " +
            "Where (:type is null or :type='' or m.type=:type) " +
            "And (:positionNo is null or p.positionNo=:positionNo) " +
            "And (:positionDetailNo is null or pd.positionDetailNo=:positionDetailNo) " +
            "And (:keyword is null or :keyword='' or " +
            "       m.subject Like '%'||:keyword||'%' " +
            "       or ml.name Like '%'||:keyword||'%' " +
            "       or c.categoryName Like '%'||:keyword||'%' " +
            "       or sc.studyCategoryName Like '%'||:keyword||'%' " +
            ")")
    Page<Moim> selectByTypeAndPositionAndKeyword(String type, Integer positionNo, Integer positionDetailNo, String keyword, Pageable pageable);

}
