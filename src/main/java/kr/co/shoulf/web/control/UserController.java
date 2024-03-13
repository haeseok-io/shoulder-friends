package kr.co.shoulf.web.control;

import kr.co.shoulf.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/detail")
    public String detail(@RequestParam("no") Long userNo, Model model) {
        model.addAttribute("userData", userService.readUserDetail(userNo));

        return "user/detail";
    }
}
