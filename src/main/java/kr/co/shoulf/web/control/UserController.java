package kr.co.shoulf.web.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @GetMapping(value = "/detail")
    public String detail() {

        return "user/detail";
    }
}
