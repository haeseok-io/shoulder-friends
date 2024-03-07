package kr.co.shoulf.web.control;

import kr.co.shoulf.web.entity.Member;
import kr.co.shoulf.web.entity.Studycafe;
import kr.co.shoulf.web.repository.MemberRepository;
import kr.co.shoulf.web.repository.StudycafeRepository;
import kr.co.shoulf.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final StudycafeRepository studycafeRepository;

    @GetMapping("/")
    public String member(Model model) {
        List<Member> memberList = memberService.memberList();

        // 각 회원의 studycafe 정보를 저장할 리스트
        List<List<Studycafe>> memberStudyCafesList = new ArrayList<>();

        for (Member member : memberList) {
            // 회원의 studycafe 정보를 조회하여 리스트에 추가
            List<Studycafe> studycafeList = studycafeRepository.findByMember_MemberNo(member.getMemberNo());
            memberStudyCafesList.add(studycafeList);
        }

        model.addAttribute("memberList", memberList);
        model.addAttribute("memberStudyCafesList", memberStudyCafesList);

        return "admin/member/list";
    }

    @GetMapping("/detail")
    public String memberDetail(@Param("memberNo")Long memberNo, Model model) {
        Optional<Member> member = memberRepository.findById(memberNo);
        model.addAttribute("member", member);
        return "admin/member/detail";
    }

    @GetMapping("/modify")
    public String memberModify(@RequestParam("memberNo")Long memberNo, Model model) {
        Optional<Member> member = memberRepository.findById(memberNo);
        model.addAttribute("member", member);
        return "admin/member/modify";
    }

    @PostMapping("/modify")
    public String memberModifyOk(@RequestParam("memberNo")Long memberNo,
                                 @RequestParam("phone")String phone,
                                 @RequestParam("post")String post,
                                 @RequestParam("addr")String addr) {
        Optional<Member> optionalMember = memberRepository.findById(memberNo);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();

            member.setPhone(phone);
            member.setPost(post);
            member.setAddr(addr);

            memberRepository.save(member);
        }
        return "redirect:/member/";
    }

    @PostMapping("/approval")
    public String memberApproval(@RequestParam("memberNo")Long memberNo,
                                 RedirectAttributes redirectAttributes) {
        Optional<Member> optionalMember = memberRepository.findById(memberNo);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();

            member.setRole("ROLE_EMP");

            memberRepository.save(member);
        }
        redirectAttributes.addFlashAttribute("message", "승인이 완료되었습니다.");
        return "redirect:/member/";
    }
}
