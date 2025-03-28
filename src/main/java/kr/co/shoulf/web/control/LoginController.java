package kr.co.shoulf.web.control;

import kr.co.shoulf.web.entity.Users;
import kr.co.shoulf.web.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @GetMapping("/login/find")
    public String find() {
        return "login/find";
    }

    @GetMapping("/login/register")
    public String register() {
        return "login/register";
    }

    @PostMapping("/login/register")
    public String registerOk(@ModelAttribute Users users) {
        loginService.registerUser(users);
        return "redirect:/";
    }
}
