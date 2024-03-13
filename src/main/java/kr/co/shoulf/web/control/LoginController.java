package kr.co.shoulf.web.control;

import kr.co.shoulf.web.entity.Users;
import kr.co.shoulf.web.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/")
    public String login() {
        return "login/login";
    }

    @PostMapping("/loginProc")
    public String loginOk(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        boolean user = loginService.login(username, password);
        if(user) {
            // 세션에 유저 정보 저장
            return "redirect:/";
        } else {
            // 로그인 실패 시
            model.addAttribute("error", "존재하지 않는 아이디이거나 비밀번호를 다시 입력해주세요.");
            return "redirect:/login/"; // 로그인 페이지로 다시 이동
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

    @PostMapping("/register")
    public String registerOk(@ModelAttribute Users users) {
        loginService.registerUser(users);
        return "redirect:/login/";
    }
}
