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
    public String boardList(@RequestParam(value = "cate", required = false) Integer cate, Model model, PageRequestDTO pageRequestDTO) {
//        List<BoardDTO> boardList;
        BoardDTO boardBest = boardService.readBest();
        model.addAttribute("boardBest", boardBest);
        PageResponseDTO<Board> boardList;
        if (cate == null) boardList = boardService.readAll(pageRequestDTO); // 전체목록
        else boardList = boardService.readCate(cate, pageRequestDTO); // 카테고리에 따른 목록

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

    @GetMapping("/write")
    public String boardWrite(Model model) {
        return "board/write";
    }

    @PostMapping("/write")
    public String writeOk(@ModelAttribute Board board, HttpServletRequest req) {
        Users user = userService.readOne(1L);

//        String contents = HtmlUtils.htmlEscape(board.getContents());

        board.setIp(req.getRemoteAddr());
        board.setCate(board.getCate());
//        board.setContents(contents);
        board.setUsers(user);
        boardService.add(board);
        return "redirect:/board/";
    }

    @PostMapping("/modify")
    public void boardModify() {
    }

    @PostMapping("/delete")
    public void boardDelete() {
    }

}
