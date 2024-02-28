package kr.co.shoulf.web.control;

import jakarta.servlet.http.HttpSession;
import kr.co.shoulf.web.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminMainController {
    @GetMapping("/")
    public String adminMain(HttpSession session, Model model) {
        // 세션에서 로그인된 사용자 정보 가져오기
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");

        // 로그인된 사용자가 있을 경우
        if(loggedInUser != null) {
            // 모델에 로그인된 사용자 정보 추가
            model.addAttribute("member", loggedInUser);
            return "admin/index"; // 관리자 메인 페이지로 이동
        } else {
            return "redirect:/admin/login/"; // 로그인 페이지로 리다이렉트
        }
    }
}
