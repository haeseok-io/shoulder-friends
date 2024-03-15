package kr.co.shoulf.web.control;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.shoulf.web.entity.Member;
import kr.co.shoulf.web.entity.Users;
import kr.co.shoulf.web.security.custom.userDetails.CustomUserDetails;
import kr.co.shoulf.web.service.ChecklistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Console;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/moim")
public class ChecklistController {
    private final ChecklistService checklistService;

    //체크리스트 목록
    @GetMapping("/checklist")
    public void checklist(@RequestParam Long moimNo, Model model, @AuthenticationPrincipal CustomUserDetails user) {
        model.addAttribute("list", checklistService.getlist(moimNo));
        model.addAttribute("moimNo", moimNo);
        model.addAttribute("userName", user.getUsername());

    }

    //체크리스트 등록
    @PostMapping("/checklist/write")
    public String checklistWrite(@RequestParam String contents, @RequestParam Long moimNo, @AuthenticationPrincipal CustomUserDetails user){
        checklistService.checklistWrite(contents, moimNo ,user);
        return "redirect:/moim/checklist?moimNo="+moimNo;
    }

    //체크리스트 미완료처리
    @PostMapping("/checklist/do")
    public String checklistDo(@RequestParam Long moimNo, @RequestParam Long checklistNo){
        checklistService.checkdo(checklistNo);
        return "redirect:/moim/checklist?moimNo="+moimNo;
    }
    
    //체크리스트 완료처리
    @PostMapping("/checklist/done")
    public String checklistDone(@RequestParam Long moimNo, @RequestParam Long checklistNo){
        checklistService.checkdone(checklistNo);
        return "redirect:/moim/checklist?moimNo="+moimNo;
    }

    //체크리스트 삭제
    @GetMapping("/checklist/delete")
    public String checklistDelete(@RequestParam Long moimNo, @RequestParam Long checklistNo){
        checklistService.deleteChecklist(checklistNo);
        return "redirect:/moim/checklist?moimNo="+moimNo;
    }
}
