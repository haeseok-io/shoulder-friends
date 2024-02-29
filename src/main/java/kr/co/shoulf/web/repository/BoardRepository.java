package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findTopByOrderByHitsDesc();
    Page<Board> findAllByCateEquals(int cate, Pageable pageable);
}
