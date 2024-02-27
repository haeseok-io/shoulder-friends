package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findTopByOrderByHitsDesc();
    List<Board> findAllByOrderByBoardNoDesc();
    List<Board> findAllByCateEquals(int cate);
}
