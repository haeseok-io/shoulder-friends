package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByGroupNo(Long groupNo);
    List<Message> findByGroupNoAndReceiver_UserNoAndStatus(Long groupNo, Long userNo, Integer status);

    // 최대 groupNo 구하기
    @Query("select max(m.groupNo) from Message m")
    Long maxByGroupNo();

    // 회원의 메세지 리스트 ( 그룹별로 가장 최신 데이터 리스트 )
    @Query("select m from Message m " +
            "where m.messageNo in (" +
                "select max(m2.messageNo) as message_no from Message m2 where m2.receiver.userNo=:userNo or m2.sender.userNo=:userNo group by m2.groupNo" +
            ") " +
            "order by m.messageNo desc")
    List<Message> selectByUserNoGroupByGroupNoOrderByMessageNoDesc(Long userNo);

    // 회원이 받은 메세지중 확인하지 않은 메세지 갯수
    @Query("select count(m) from Message m " +
            "where m.messageNo in (select max(m2.messageNo) as message_no from Message m2 where m2.receiver.userNo=:userNo group by m2.groupNo) " +
            "And m.status=1")
    Long newReceivedCountByUserNo(Long userNo);
}
