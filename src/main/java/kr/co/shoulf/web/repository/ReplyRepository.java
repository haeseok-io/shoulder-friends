package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByParentReplyIsNotNullAndBoard_BoardNo(Long boardNo);
    List<Reply> findByBoard_BoardNoOrderByRegdateDesc(Long boardNo);
}
