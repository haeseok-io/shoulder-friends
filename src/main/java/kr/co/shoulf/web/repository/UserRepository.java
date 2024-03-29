package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
    Users findByEmailAndOauth2Where(String email, String oauth2);
    List<Users> findTop12ByOrderByUserNoDesc();
    boolean existsByEmail(String email);
    Users findByNickname(String nickName);

//    @Query("Select uj From UserJob uj, UserLanguage ul " +
//            "Left Join fetch uj.users u " +
//            "Left Join fetch uj.positionDetail pd " +
//            "Left Join pd.position p " +
//            "Where (:positionNo is null or p.positionNo=:positionNo) " +
//            "And (:positionDetailNo is null or pd.positionDetailNo=:positionDetailNo) " +
//            "And (:keyword is null or :keyword='' or " +
//            "       u.nickname Like '%'||:keyword||'%' " +
//            "       or p.bigName Like '%'||:keyword||'%' " +
//            "       or pd.middleName Like '%'||:keyword||'%' " +
//            "       or ul.name Like '%'||:keyword||'%' " +
//            ")")

    @Query("Select u From Users u " +
            "left join u.userJob uj " +
            "left join u.userLanguageList ul " +
            "Where (:positionNo is null or uj.positionDetail.position.positionNo=:positionNo) " +
            "And (:positionDetailNo is null or uj.positionDetail.positionDetailNo=:positionDetailNo) " +
            "And (:keyword is null or :keyword='' or " +
            "       u.nickname like '%'||:keyword||'%' " +
            "       or uj.positionDetail.position.bigName like '%'||:keyword||'%' " +
            "       or uj.positionDetail.middleName like '%'||:keyword||'%' " +
            "       or ul.name like '%'||:keyword||'%'" +
            ")")
    List<Users> selectByTypeAndPositionAndKeyword(Integer positionNo, Integer positionDetailNo, String keyword);
}
