package kr.co.shoulf.web.control;

import jakarta.servlet.http.HttpSession;
import kr.co.shoulf.web.entity.Member;
import kr.co.shoulf.web.entity.MemberCommute;
import kr.co.shoulf.web.service.CommuteService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/commute")
@Getter
public class CommuteController {
    private final CommuteService commuteService;

    @GetMapping("/")
    public String commuteMain(HttpSession session, Model model) {
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        if(loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser);
        }

        return "admin/commute/index";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<MemberCommute> readCommuteByData(@RequestParam("selectedDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date selectedDate) {
        return commuteService.readMemberCommuteBySelectedDate(selectedDate);
    }

    @PostMapping("/in")
    @ResponseBody
    public ResponseEntity<String> commuteIn(HttpSession session,
                                            @RequestParam("selectedDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date selectedDate) {
        Member member = (Member) session.getAttribute("loggedInUser");
        commuteService.updateInTime(member.getMemberNo(), selectedDate);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @PostMapping("/out")
    @ResponseBody
    public ResponseEntity<String> commuteOut(HttpSession session,
                                             @RequestParam("selectedDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date selectedDate) {
        Member member = (Member) session.getAttribute("loggedInUser");
        commuteService.updateOutTime(member.getMemberNo(), selectedDate);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @PostMapping("/insertTemporaryData")
    public String insertData() {
        commuteService.insertCommuteData();
        return "redirect:/commute/";
    }
}
