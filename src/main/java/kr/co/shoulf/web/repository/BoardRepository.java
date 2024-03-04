package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("select b from Board b where b.regdate >= :weekAgo order by b.hits desc ")
    Page<Board> findTopByHitsWithinLastWeek(@Param("weekAgo") LocalDateTime weekAgo, Pageable pageable);
    Page<Board> findAllByCateEquals(int cate, Pageable pageable);
    Page<Board> findByTitleContaining(String keyword, Pageable pageable);
    Page<Board> findByContentsContaining(String keyword, Pageable pageable);
}
