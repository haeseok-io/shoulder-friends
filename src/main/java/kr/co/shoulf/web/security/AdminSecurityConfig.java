package kr.co.shoulf.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Order(1)
public class AdminSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/admin/**").hasAnyRole("admin","emp") // admin 및 emp 역할이 있는 사용자만 해당 URL에 접근 가능
                        .anyRequest().permitAll() // 그 외의 요청은 모두 허용
                );
        // 로그인 설정
        http
                .formLogin((auth) -> auth
                        .loginPage("/admin/login/") // 로그인 페이지 설정
                        .loginProcessingUrl("/admin/loginProc").permitAll() // 로그인 처리 URL 설정
                        .defaultSuccessUrl("/admin/") // 로그인 성공 시 기본 URL로 이동
                );
        // CSRF 보안 설정 비활성화
        http
                .csrf((auth) -> auth.disable());
        return http.build();
    }
}
