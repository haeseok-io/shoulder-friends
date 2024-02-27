package kr.co.shoulf.web.service;

import kr.co.shoulf.web.dto.BoardDTO;
import kr.co.shoulf.web.entity.Board;
import kr.co.shoulf.web.repository.BoardRepository;
import kr.co.shoulf.web.repository.ReplyRepository;
import kr.co.shoulf.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final ReplyRepository replyRepository;

    public BoardDTO readBest() {
        BoardDTO boardDTO = BoardDTO.builder()
                .board(boardRepository.findTopByOrderByHitsDesc())
                .build();
        return boardDTO;
    }

    public List<BoardDTO> readAll() {
        List<BoardDTO> list = new ArrayList<>();
        boardRepository.findAllByOrderByBoardNoDesc().forEach(board -> {
            list.add(
                    BoardDTO.builder()
                            .board(board)
                            .build()
            );
        });
        return list;
    }

    public List<BoardDTO> readCate(int cate) {
        List<BoardDTO> list = new ArrayList<>();
        boardRepository.findAllByCateEquals(cate).forEach(board -> {
            list.add(
                    BoardDTO.builder()
                            .board(board)
                            .build()
            );
        });
        System.out.println(list);
        return list;
    }

    public BoardDTO readOne(Long boardNo) {
        Optional<Board> result = boardRepository.findById(boardNo);
        BoardDTO boardDTO = BoardDTO.builder()
                .board(result.get())
                .build();

        return boardDTO;
    }
}
