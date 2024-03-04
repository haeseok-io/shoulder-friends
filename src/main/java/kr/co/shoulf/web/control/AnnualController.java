package kr.co.shoulf.web.control;

import jakarta.servlet.http.HttpSession;
import kr.co.shoulf.web.entity.Member;
import kr.co.shoulf.web.entity.MemberAnnual;
import kr.co.shoulf.web.entity.MemberAnnualDetail;
import kr.co.shoulf.web.repository.MemberRepository;
import kr.co.shoulf.web.service.AnnualService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/annual")
public class AnnualController {
    private final MemberRepository memberRepository;
    private final AnnualService annualService;

    @GetMapping("/")
    public String annualMain(HttpSession session, Model model) {
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser);
            return "admin/annual/index";
        } else {
            return "redirect:/admin/login/";
        }
    }

    @GetMapping("/list")
    public String annualList(Model model) {
        List<MemberAnnualDetail> memberAnnualDetailList = annualService.getAnnualDetailList();
        model.addAttribute("memberAnnualDetailList", memberAnnualDetailList);
        return "admin/annual/list";
    }

    @GetMapping("/list/detail")
    public String annualListDetail(Model model, @RequestParam("memberNo")Long memberNo) {
        Member member = memberRepository.findById(memberNo).orElse(null);

        List<MemberAnnual> annualList = annualService.getAnnualListByMemberNo(memberNo);
        MemberAnnualDetail totalAnnualNum = annualService.totalAnnualNum(member, memberNo);
        List<MemberAnnualDetail> annualDetailList =  annualService.getAnnualDetailListByMemberNo(member, memberNo);

        model.addAttribute("annualList", annualList);
        model.addAttribute("totalAnnualNum", totalAnnualNum);
        model.addAttribute("annualDetailList", annualDetailList);

        return "admin/annual/detail";
    }

    @PutMapping("/approve")
    public ResponseEntity<String> updateAnnualDetailStatusApprove(@RequestParam("annualDetailNo")Long annualDetailNo,
                                                                  @RequestParam("memberNo")Long memberNo) {
        annualService.approveAnnual(annualDetailNo, memberNo);
        return ResponseEntity.ok("승인 성공");
    }

    @PutMapping("/reject")
    public ResponseEntity<String> updateAnnualDetailStatusReject(@RequestParam("annualDetailNo")Long annualDetailNo,
                                                                 @RequestParam("memberNo")Long memberNo) {
        annualService.rejectAnnual(annualDetailNo, memberNo);
        return ResponseEntity.ok("반려 성공");
    }

    @GetMapping("/myList")
    public String annualMyList(HttpSession session, Model model) {
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");

        if(loggedInUser != null) {
            List<MemberAnnual> annualList = annualService.getAnnualListByMemberNo(loggedInUser.getMemberNo());
            MemberAnnualDetail totalAnnualNum = annualService.totalAnnualNum(loggedInUser, loggedInUser.getMemberNo());
            List<MemberAnnualDetail> annualDetailList =  annualService.getAnnualDetailListByMemberNo(loggedInUser, loggedInUser.getMemberNo());

            model.addAttribute("annualList", annualList);
            model.addAttribute("totalAnnualNum", totalAnnualNum);
            model.addAttribute("annualDetailList", annualDetailList);

            return "admin/annual/myList";
        }else {
            return "redirect:/admin/login/";
        }
    }

    @GetMapping("/write")
    public String annualWrite(HttpSession session, Model model) {
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");

        if(loggedInUser != null) {
            List<MemberAnnualDetail> annualDetailList =  annualService.getAnnualDetailListByMemberNo(loggedInUser, loggedInUser.getMemberNo());
            model.addAttribute("annualDetailList", annualDetailList);
        }
        return "admin/annual/write";
    }

    @PostMapping("/writeOk")
    public String annualWriteOk(HttpSession session,
                                @RequestParam("useCate")int useCate,
                                @RequestParam("startDate")String startDate,
                                @RequestParam("endDate")String endDate,
                                @RequestParam("annualNo")Long annualDate) {
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
        annualService.insertAnnualDetail(loggedInUser, useCate, startDate, endDate, annualDate);
        return "redirect:/annual/write";
    }
}