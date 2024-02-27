package kr.co.shoulf.web.control;

import kr.co.shoulf.web.dto.BoardDTO;
import kr.co.shoulf.web.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/")
    public String boardList(@RequestParam(value = "cate", required = false) Integer cate, Model model){
        List<BoardDTO> boardList;
        System.out.println(cate);
        BoardDTO boardBest = boardService.readBest();
        model.addAttribute("boardBest",boardBest);

        if(cate == null)    boardList = boardService.readAll(); // 전체목록
        else                boardList = boardService.readCate(cate); // 카테고리에 따른 목록

        model.addAttribute("boardList",boardList);
        return "board/list";
    }

    @GetMapping("/detail")
    public String boardDetail(@RequestParam(value = "boardNo", required = false) Long boardNo, Model model){
        BoardDTO boardDetail = boardService.readOne(boardNo);
        model.addAttribute("boardDetail", boardDetail);
        System.out.println(boardDetail);
        return "board/detail";
    }

    @PostMapping("/write")
    public void boardWrite(){
    }

    @PostMapping("/modify")
    public void boardModify(){
    }

    @PostMapping("/delete")
    public void boardDelete(){
    }

}
