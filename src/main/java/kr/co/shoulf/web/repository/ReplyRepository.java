package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByParentReplyIsNotNullAndBoard_BoardNo(Long boardNo);
    List<Reply> findByBoard_BoardNoOrderByRegdateDesc(Long boardNo);
    @Query("SELECT r FROM Reply r JOIN FETCH r.children WHERE r.replyNo = :replyNo")
    List<Reply> findReplyWithChildrenByReplyNo(@Param("replyNo") Long replyNo);
}
