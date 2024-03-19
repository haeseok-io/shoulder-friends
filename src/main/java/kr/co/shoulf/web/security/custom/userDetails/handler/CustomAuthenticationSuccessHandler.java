package kr.co.shoulf.web.security.custom.userDetails.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.shoulf.web.entity.Users;
import kr.co.shoulf.web.security.custom.userDetails.CustomUserDetails;
import kr.co.shoulf.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String redirectUrl = request.getParameter("redirect");

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Users users = userService.readOne(customUserDetails.getUserNo());

        // 로그인 된 정보 세션에 담기
        HttpSession session = request.getSession();
        session.setAttribute("loggedInUser", users);

        // 리다이렉트 url 이 없을경우 메인으로 이동
        if( redirectUrl==null || redirectUrl.isEmpty() ) {
            redirectUrl = "/";
        }

        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write(redirectUrl);
    }
}
