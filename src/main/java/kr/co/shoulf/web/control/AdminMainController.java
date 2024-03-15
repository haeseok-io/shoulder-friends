package kr.co.shoulf.web.control;

import jakarta.servlet.http.HttpSession;
import kr.co.shoulf.web.entity.Member;
import kr.co.shoulf.web.security.custom.userDetails.CustomMemberUserDetails;
import kr.co.shoulf.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Iterator;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminMainController {
    private final MemberService memberService;

    @GetMapping("/")
    public String adminMain(HttpSession session, Model model,
                            @AuthenticationPrincipal CustomMemberUserDetails member) {
        // 현재 사용자의 인증 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("id", authentication.getName());

        // 현재 사용자의 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();

        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        model.addAttribute("role", role);

        Member loggedInUser = memberService.loginMember(authentication.getName());

        session.setAttribute("loggedInUser", loggedInUser);

//        System.out.println(member.getUsername());
        return "/admin/index";
    }
}
