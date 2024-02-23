package kr.co.shoulf.web.control;

import kr.co.shoulf.web.service.MoimService;
import kr.co.shoulf.web.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
    private final MoimService moimService;
    private final UserService userService;

    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("moimBestList", moimService.readBest());
        model.addAttribute("userNewList", userService.readNewUsers());
        return "index";
    }
}
