package kr.co.shoulf.web.control;

import jakarta.servlet.http.HttpSession;
import kr.co.shoulf.web.entity.Users;
import kr.co.shoulf.web.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/")
    public String login() {
        return "login/login";
    }

    @PostMapping("/")
    public String loginOk(@ModelAttribute Users users, Model model, HttpSession session) {
        if(loginService.login(users)) {
            System.out.println(users);
            session.setAttribute("loggedInUser", users);
            return "redirect:/";
        }else {
            // 로그인 실패 시
            model.addAttribute("error", "존재하지 않는 아이디이거나 비밀번호를 다시 입력해주세요.");
            return "login/login"; // 로그인 페이지로 다시 이동
        }
    }

    @GetMapping("/find")
    public String find() {
        return "login/find";
    }

    @GetMapping("/register")
    public String register() {
        return "login/register";
    }
}
