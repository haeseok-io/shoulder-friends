package kr.co.shoulf.web.security;

import kr.co.shoulf.web.security.auth.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Order(2)
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("----- 시큐리티 적용 -----");
        http
                // CSRF 보안 설정 비활성화
                .csrf((auth) -> auth.disable())
                // 권한 설정
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/user/", "/moim/", "/moim/detail/").permitAll() // 로그인 페이지와 관련된 요청은 인증 없이 허용
                        .requestMatchers("/user/**", "/moim/**", "/moimLike/**", "/message", "/board/write", "/board/modify").hasRole("USER")
                        .anyRequest().permitAll() // 그 외의 요청은 모두 허용
                )
                // 로그인 설정
                .formLogin((auth) -> auth
                        .loginPage("/login/") // 로그인 페이지 설정
                        .loginProcessingUrl("/login/loginProc") // 로그인 처리 URL 설정
                        .defaultSuccessUrl("/") // 로그인 성공 시 기본 URL로 이동
                )
                // 로그아웃 설정
                .logout(logout -> logout
                    .logoutUrl("/logout") // 로그아웃 URL 설정
                    .logoutSuccessUrl("/") // 로그아웃 성공 시 리다이렉트 URL 설정
                    .invalidateHttpSession(true) // 세션 무효화 설정
                    .deleteCookies("JSESSIONID") // 삭제할 쿠키 설정
                )
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/login/") // 로그인 페이지 설정
                        .userInfoEndpoint(userInfo -> // 사용자 정보 엔드포인트 설정
                                userInfo.userService(customOAuth2UserService) // 사용자 정보 서비스 지정
                        )
                );

        return http.build();
    }
}
