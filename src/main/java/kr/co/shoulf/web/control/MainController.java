package kr.co.shoulf.web.control;

import kr.co.shoulf.web.service.MoimService;
import kr.co.shoulf.web.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Iterator;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
    private final MoimService moimService;
    private final UserService userService;

    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("moimBestList", moimService.readBestList());
        model.addAttribute("userNewList", userService.readNewUserList());

        // 현재 사용자의 인증 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("id", authentication.getName());

        // 현재 사용자의 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();

        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        model.addAttribute("role", role);

        return "index";
    }
}
