package kr.co.shoulf.web.control;

import jakarta.servlet.http.HttpSession;
import kr.co.shoulf.web.entity.Users;
import kr.co.shoulf.web.service.*;
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
    private final MoimService moimService;

    @GetMapping(value = "/user")
    public String user(Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("loggedInUser");
        Long userNo = user.getUserNo();

        model.addAttribute("userData", userService.readUserDetail(userNo));
        model.addAttribute("positionList", positionService.readAll());
        model.addAttribute("onlineList", onlineService.readAll());
        model.addAttribute("categoryList", categoryService.readAll());

        return "mypage/user";
    }

    @GetMapping("/moim")
    public String moim(Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("loggedInUser");
        Long userNo = user.getUserNo();

        model.addAttribute("selfList", moimService.readSelfWriteList(userNo));


        return "mypage/moim";
    }
}
