package kr.co.shoulf.web.security.custom.userDetails.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.shoulf.web.entity.Users;
import kr.co.shoulf.web.security.auth.dto.CustomOAuth2User;
import kr.co.shoulf.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomOAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        Users users = userService.readOne(customOAuth2User.getUserNo());

        HttpSession session = request.getSession();
        session.setAttribute("loggedInUser", users);

        response.sendRedirect("/");
    }
}
