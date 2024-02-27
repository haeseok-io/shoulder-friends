package kr.co.shoulf.web.control;

import jakarta.servlet.http.HttpSession;
import kr.co.shoulf.web.entity.Member;
import kr.co.shoulf.web.service.AdminLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminLoginController {
    private final AdminLoginService adminLoginService;

    @GetMapping("/login/")
    public String adminLogin() {
        return "/admin/login/login";
    }

    @PostMapping("/loginProc")
    public String loginOk(@ModelAttribute Member member, Model model, HttpSession session) {
        Member memberDetails = adminLoginService.adminLogin(member);
        if(memberDetails != null) {
            // 세션에 유저 정보 저장
            session.setAttribute("loggedInUser", memberDetails);
            System.out.println("memberDetails : " + memberDetails);
            return "redirect:/admin/";
        } else {
            // 로그인 실패 시
            model.addAttribute("error", "존재하지 않는 아이디이거나 비밀번호를 다시 입력해주세요.");
            return "/admin/login/login"; // 로그인 페이지로 다시 이동
        }
    }

    @GetMapping("/login/register")
    public String memberRegister() {
        return "admin/login/register";
    }

    @PostMapping("/login/register")
    public String memberRegisterOk(@ModelAttribute Member member) {
        adminLoginService.registerMember(member);
        return "redirect:/admin/login/";
    }
}
