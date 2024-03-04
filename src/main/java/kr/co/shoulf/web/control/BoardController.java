package kr.co.shoulf.web.control;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.shoulf.web.dto.BoardDTO;
import kr.co.shoulf.web.dto.PageRequestDTO;
import kr.co.shoulf.web.dto.PageResponseDTO;
import kr.co.shoulf.web.entity.Board;
import kr.co.shoulf.web.entity.Users;
import kr.co.shoulf.web.service.BoardService;
import kr.co.shoulf.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final UserService userService;

    @GetMapping("/")
    public String boardList(@RequestParam(value = "cate", required = false) Integer cate
            , @RequestParam(value = "keyword", required = false) String keyword
            , @RequestParam(value = "searchType", required = false) String searchType
            , Model model
            , PageRequestDTO pageRequestDTO) {
        BoardDTO boardBest = boardService.readBest();
        model.addAttribute("boardBest", boardBest);

        PageResponseDTO<Board> boardList = null;
        if (cate == null && keyword == null) {
            boardList = boardService.readAll(pageRequestDTO); // 전체목록
        } else if (cate != null) {
            boardList = boardService.readCate(cate, pageRequestDTO); // 카테고리에 따른 목록
        } else if (keyword != null) {
            if ("title".equals(searchType)) {
                boardList = boardService.searchByTitle(keyword, pageRequestDTO);
            } else if("contents".equals(searchType)){
                boardList = boardService.searchByContents(keyword, pageRequestDTO);
            }
        }

        model.addAttribute("boardList", boardList);
        System.out.println(boardList);
        return "board/list";
    }

    @GetMapping("/detail")
    public String boardDetail(@RequestParam(value = "boardNo", required = false) Long boardNo, Model model) {
        BoardDTO boardDetail = boardService.readOne(boardNo);
        model.addAttribute("boardDetail", boardDetail);
        System.out.println(boardDetail);
        return "board/detail";
    }

    @PostMapping("/plusHits")
    @ResponseBody
    public void plusHits(@RequestParam Long boardNo) {
        System.out.println("plusHits 호출");
        BoardDTO boardDTO = boardService.readOne(boardNo);
        Board board = boardDTO.getBoard();
        board.setHits(board.getHits() + 1);
        boardService.add(board);
//        boardDTO.setBoard(board);
//        return boardDTO;
    }

    @GetMapping("/write")
    public String boardWrite() {
        return "board/write";
    }

    @PostMapping("/write")
    public String writeOk(@ModelAttribute Board board, HttpServletRequest req) {
        Users user = userService.readOne(1L);
        board.setIp(req.getRemoteAddr());
        board.setCate(board.getCate());
        board.setUsers(user);
        boardService.add(board);
        return "redirect:/board/";
    }

    @GetMapping("/modify")
    public String modify(@RequestParam(value = "boardNo", required = false) Long boardNo, Model model) {
        BoardDTO boardDetail = boardService.readOne(boardNo);
        model.addAttribute("boardDetail", boardDetail);
        System.out.println(boardDetail);
        return "board/modify";
    }

    @PostMapping("/delete")
    public void boardDelete() {
    }

}
