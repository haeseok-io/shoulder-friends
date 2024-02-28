package kr.co.shoulf.web.security;

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
@Order(2)
public class SecurityConfig{
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("----- 시큐리티 적용 -----");
        // 권한 설정
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login/**").permitAll() // 로그인 페이지와 관련된 요청은 인증 없이 허용
                        .anyRequest().permitAll() // 그 외의 요청은 모두 허용
                );
        // 로그인 설정
        http
                .formLogin((auth) -> auth
                        .loginPage("/login/") // 로그인 페이지 설정
                        .loginProcessingUrl("/login/loginProc").permitAll() // 로그인 처리 URL 설정
                        .defaultSuccessUrl("/") // 로그인 성공 시 기본 URL로 이동
                );
        // CSRF 보안 설정 비활성화
        http
                .csrf((auth) -> auth.disable());
        return http.build();
    }

}
