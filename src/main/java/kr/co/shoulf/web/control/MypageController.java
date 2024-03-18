package kr.co.shoulf.web.control;

import kr.co.shoulf.web.service.CategoryService;
import kr.co.shoulf.web.service.OnlineService;
import kr.co.shoulf.web.service.PositionService;
import kr.co.shoulf.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/mypage")
public class MypageController {
    private final UserService userService;
    private final PositionService positionService;
    private final OnlineService onlineService;
    private final CategoryService categoryService;

    @GetMapping(value = "/user")
    public String user(Model model) {
        // 테스트 유저
        Long userNo = 10L;

        model.addAttribute("userData", userService.readUserDetail(userNo));
        model.addAttribute("positionList", positionService.readAll());
        model.addAttribute("onlineList", onlineService.readAll());
        model.addAttribute("categoryList", categoryService.readAll());

        return "mypage/user";
    }
}
