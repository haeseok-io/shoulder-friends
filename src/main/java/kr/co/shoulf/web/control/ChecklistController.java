package kr.co.shoulf.web.control;

import kr.co.shoulf.web.service.ChecklistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/moim")
public class ChecklistController {
    private final ChecklistService checklistService;

    //체크리스트 목록
    @GetMapping("/checklist")
    public void checklist(@RequestParam Long moimNo, Model model) {
        model.addAttribute("list", checklistService.getlist(moimNo));
        model.addAttribute("moimNo", moimNo);
    }

    //체크리스트 등록
    @PostMapping("/checklist/write")
    public String checklistWrite(@RequestParam String contents, @RequestParam Long moimNo){
        checklistService.checklistWrite(contents, moimNo);
        return "redirect:/moim/checklist?moimNo="+moimNo;
    }

    //체크리스트 미완료처리
    @PostMapping("/checklist/do")
    public String checklistDo(@RequestParam Long moimNo){
        return "redirect:/moim/checklist?moimNo="+moimNo;
    }
    
    //체크리스트 완료처리
    @PostMapping("/checklist/done")
    public String checklistDone(@RequestParam Long moimNo){
        return "redirect:/moim/checklist?moimNo="+moimNo;
    }

    //체크리스트 삭제
    @PostMapping("/checklist/delete")
    public String checklistDelete(@RequestParam Long moimNo){
        return "redirect:/moim/checklist?moimNo="+moimNo;
    }
}
