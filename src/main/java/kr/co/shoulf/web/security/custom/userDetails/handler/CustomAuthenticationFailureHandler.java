package kr.co.shoulf.web.security.custom.userDetails.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMsg = "";
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if( username==null || username.isEmpty() ) {
            errorMsg = "회원 이메일 정보가 없습니다.";
        } else if( password==null || password.isEmpty() ) {
            errorMsg = "비밀번호 정보가 없습니다.";
        } else {
            if( exception instanceof UsernameNotFoundException ){
                errorMsg = exception.getMessage();
            }
        }

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(errorMsg);
    }
}
