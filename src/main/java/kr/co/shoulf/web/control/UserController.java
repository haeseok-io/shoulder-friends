package kr.co.shoulf.web.control;

import jakarta.servlet.http.HttpSession;
import kr.co.shoulf.web.dto.*;
import kr.co.shoulf.web.entity.Users;
import kr.co.shoulf.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @GetMapping(value = "/simple/json", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> simpleData(HttpSession session) {
        Users loggedInUser = (Users) session.getAttribute("loggedInUser");
        if( loggedInUser==null ) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 정보가 존재하지 않습니다.");
        }

        return ResponseEntity.ok().body(userService.readUserDetail(loggedInUser.getUserNo()));
    }

    @PostMapping(value = "/modify")
    public String modify(UserDataRequestDTO userDataRequestDTO) {
        Map<Boolean, String> isOk = userService.modifyOne(userDataRequestDTO);


        return "redirect:/mypage/user";
    }
}
