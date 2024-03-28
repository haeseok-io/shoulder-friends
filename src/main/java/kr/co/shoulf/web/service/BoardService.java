package kr.co.shoulf.web.service;

import kr.co.shoulf.web.dto.BoardDTO;
import kr.co.shoulf.web.dto.PageRequestDTO;
import kr.co.shoulf.web.dto.PageResponseDTO;
import kr.co.shoulf.web.entity.Board;
import kr.co.shoulf.web.repository.BoardRepository;
import kr.co.shoulf.web.repository.ReplyRepository;
import kr.co.shoulf.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;
    public BoardDTO readBest() {
        LocalDateTime weekAgo = LocalDateTime.now().minusDays(7);
        Page<Board> boards = boardRepository.findTopByHitsWithinLastWeek(weekAgo, PageRequest.of(0,1));
        BoardDTO boardDTO = BoardDTO.builder()
                .board(boards.getContent().isEmpty() ? null : boards.getContent().get(0))
                .build();
        return boardDTO;
    }

    public PageResponseDTO<Board> readAll(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("boardNo");
        log.info("pageable : {}", pageable);
        Page<Board> result = boardRepository.findAll(pageable);
        List<Board> list = result.getContent().stream().collect(Collectors.toList());

        PageResponseDTO<Board> dto = PageResponseDTO.<Board>pageBuilder()
                .pageRequestDTO(pageRequestDTO)
                .dataList(list)
                .total((int) result.getTotalElements())
                .build();
        return dto;
    }

    public PageResponseDTO<Board> readCate(int cate , PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("boardNo");
        log.info("pageable : {}", pageable);

        Page<Board> result = boardRepository.findAllByCateEquals(cate,pageable);
        List<Board> list = result.getContent().stream().collect(Collectors.toList());
        PageResponseDTO<Board> dto = PageResponseDTO.<Board>pageBuilder()
                .pageRequestDTO(pageRequestDTO)
                .dataList(list)
                .total((int) result.getTotalElements())
                .build();
        return dto;
    }

    public PageResponseDTO<Board> searchByTitle(String keyword, PageRequestDTO pageRequestDTO){
        Pageable pageable = pageRequestDTO.getPageable("title");
        log.info("pageable : {}", pageable);

        Page<Board> result = boardRepository.findByTitleContaining(keyword, pageable);
        List<Board> list = result.getContent().stream().collect(Collectors.toList());
        PageResponseDTO<Board> dto = PageResponseDTO.<Board>pageBuilder()
                .pageRequestDTO(pageRequestDTO)
                .dataList(list)
                .total((int) result.getTotalElements())
                .build();
        return dto;
    }
    public PageResponseDTO<Board> searchByContents(String keyword, PageRequestDTO pageRequestDTO){
        Pageable pageable = pageRequestDTO.getPageable( "contents");
        log.info("pageable : {}", pageable);

        Page<Board> result = boardRepository.findByContentsContaining(keyword, pageable);
        List<Board> list = result.getContent().stream().collect(Collectors.toList());
        PageResponseDTO<Board> dto = PageResponseDTO.<Board>pageBuilder()
                .pageRequestDTO(pageRequestDTO)
                .dataList(list)
                .total((int) result.getTotalElements())
                .build();
        return dto;
    }

    public BoardDTO readOne(Long boardNo) {
        Optional<Board> result = boardRepository.findById(boardNo);
        BoardDTO boardDTO = BoardDTO.builder()
                .board(result.get())
                .build();

        return boardDTO;
    }

    public void add(Board board) {
        boardRepository.save(board);
    }

    public void remove(Long boardNo) {
        boardRepository.deleteById(boardNo);
    }
}
