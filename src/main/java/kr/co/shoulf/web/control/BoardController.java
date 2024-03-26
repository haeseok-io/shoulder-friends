package kr.co.shoulf.web.control;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.shoulf.web.dto.BoardDTO;
import kr.co.shoulf.web.dto.PageRequestDTO;
import kr.co.shoulf.web.dto.PageResponseDTO;
import kr.co.shoulf.web.entity.Board;
import kr.co.shoulf.web.entity.Reply;
import kr.co.shoulf.web.entity.Users;
import kr.co.shoulf.web.service.BoardService;
import kr.co.shoulf.web.service.ReplyService;
import kr.co.shoulf.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final ReplyService replyService;
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
            } else if ("contents".equals(searchType)) {
                boardList = boardService.searchByContents(keyword, pageRequestDTO);
            }
        }

        model.addAttribute("boardList", boardList);
//        System.out.println(boardList);
        return "board/list";
    }

    @GetMapping("/detail")
    public String boardDetail(@RequestParam(value = "boardNo", required = false) Long boardNo, Model model, HttpSession session) {
        BoardDTO boardDetail = boardService.readOne(boardNo);
        model.addAttribute("boardDetail", boardDetail);

        List<Reply> replyList = replyService.readAll(boardNo);
        List<Reply> childReply = replyService.readChild(boardNo);
        model.addAttribute("replyList", replyList);
        model.addAttribute("childReply", childReply);

        String br = System.getProperty("line.separator").toString();
        model.addAttribute( "nlString", br);

        Users loggedInUser = (Users) session.getAttribute("loggedInUser");
        Users boardUser = boardDetail.getBoard().getUsers();
        if(Objects.equals(loggedInUser.getUserNo(), boardUser.getUserNo())) model.addAttribute("sameUser", true);
        return "board/detail";
    }

    @PostMapping("/replyWrite")
    public String replyWrite(@ModelAttribute Reply reply, @RequestParam("boardNo") Long boardNo, HttpSession session){
        Board board = boardService.readOne(boardNo).getBoard();
        Users loggedInUser = (Users) session.getAttribute("loggedInUser");
        reply.setBoard(board);
        reply.setUsers(loggedInUser);
        reply.setDepth(1);
        replyService.write(reply);
        return "redirect:/board/detail?boardNo=" + board.getBoardNo();
    }

    @PostMapping("/writeChildReply")
    public String writeChildReply(@ModelAttribute Reply reply, @RequestParam("replyNo") Long replyNo, @RequestParam("boardNo") Long boardNo, HttpSession session){
        Board board = boardService.readOne(boardNo).getBoard();
        Users loggedInUser = (Users) session.getAttribute("loggedInUser");
        Reply parent = replyService.readOne(replyNo);
        System.out.println("parent:"+parent);
        System.out.println("-------------------------");
        Reply child = new Reply();
        Integer depth = null;
        Optional<Reply> latestChild = parent.getChildren().stream()
                .sorted(Comparator.comparing(Reply::getRegdate).reversed())
                .findFirst();
        if (latestChild.isPresent()) {
            depth = latestChild.get().getDepth();
        }
        child.setBoard(board);
        child.setUsers(loggedInUser);
        child.setParentReply(parent);
        child.setContents(reply.getContents());
        if(depth == null) child.setDepth(2);
        else child.setDepth(depth + 1);
        System.out.println("최근댓글 뎁스? " + latestChild.orElse(null));
        System.out.println(child);
        replyService.write(child);
        return "redirect:/board/detail?boardNo=" + board.getBoardNo();
    }


    @GetMapping("/getChild")
    @ResponseBody
    public List<Reply> getChild(@RequestParam Long replyNo){
        List<Reply> list = replyService.readRecomments(replyNo);
//        System.out.println(replyNo + "--------------------------------"+ list);
        return list;
    }
    @PostMapping("/plusHits")
    @ResponseBody
    public void plusHits(@RequestParam Long boardNo) {
        BoardDTO boardDTO = boardService.readOne(boardNo);
        Board board = boardDTO.getBoard();
        board.setHits(board.getHits() + 1);
        boardService.add(board);
    }

    @GetMapping("/write")
    public String boardWrite() {
        return "board/write";
    }

    @PostMapping("/write")
    public String writeOk(@ModelAttribute Board board, HttpServletRequest req, HttpSession session) {
        Users loggedInUser = (Users) session.getAttribute("loggedInUser");
        board.setIp(req.getRemoteAddr());
        board.setCate(board.getCate());
        board.setUsers(loggedInUser);
        boardService.add(board);
        return "redirect:/board/";
    }

    @GetMapping("/modify") //수정폼 이동
    public String modify(@RequestParam(value = "boardNo", required = false) Long boardNo, Model model) {
        BoardDTO boardDetail = boardService.readOne(boardNo);
        model.addAttribute("boardDetail", boardDetail.getBoard());
        return "board/modify";
    }

    @PostMapping("/modify")
    public String modifyOk(Board board, HttpServletRequest req) {
        BoardDTO boardDTO = boardService.readOne(board.getBoardNo());
        board.setIp(req.getRemoteAddr());
        board.setCate(board.getCate());
        board.setUsers(boardDTO.getBoard().getUsers());
        boardService.add(board);
        return "redirect:/board/detail?boardNo=" + board.getBoardNo();
    }


    @RequestMapping("/delete")
    public String boardDelete(@RequestParam(value = "boardNo", required = false) Long boardNo) {
        boardService.remove(boardNo);
        return "redirect:/board/";
    }



}
