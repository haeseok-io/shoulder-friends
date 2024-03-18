package kr.co.shoulf.web.security.custom.userDetails.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.shoulf.web.entity.Users;
import kr.co.shoulf.web.security.custom.userDetails.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Users users = Users.builder()
                .userNo(customUserDetails.getUserNo())
                .email(customUserDetails.getUsername())
                .build();

        // 로그인 된 정보 세션에 담기
        HttpSession session = request.getSession();
        session.setAttribute("loggedInUser", users);

        response.sendRedirect("/");
    }
}
