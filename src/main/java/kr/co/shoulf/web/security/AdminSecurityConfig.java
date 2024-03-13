package kr.co.shoulf.web.security;

import kr.co.shoulf.web.security.custom.userDetails.service.CustomMemberUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Order(1)
public class AdminSecurityConfig {
    private final CustomMemberUserDetailsService customMemberUserDetailsService;

    @Bean
    public UserDetailsService userMemberDetailsService() {
        return customMemberUserDetailsService;
    }

    @Bean
    public PasswordEncoder memberPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider memberDaoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userMemberDetailsService());
        provider.setPasswordEncoder(memberPasswordEncoder());

        return provider;
    }

    @Bean
    public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
        log.info("----- 시큐리티 적용1 -----");
        http
                // CSRF 보안 설정 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(memberDaoAuthenticationProvider())
                // 권한 설정
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/admin/login/**").permitAll()
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN","EMP") // admin 및 emp 역할이 있는 사용자만 해당 URL에 접근 가능
                        .anyRequest().permitAll() // 그 외의 요청은 모두 허용
                )
                // 로그인 설정
                .formLogin((auth) -> auth
                        .loginPage("/admin/login/") // 로그인 페이지 설정
                        .loginProcessingUrl("/admin/loginProc") // 로그인 처리 URL 설정
                        .failureUrl("/admin/login/?error") // 로그인 실패 시 이동할 URL 설정
                        .defaultSuccessUrl("/admin/") // 로그인 성공 시 기본 URL로 이동
                )
                // 로그아웃 설정
                .logout(logout -> logout
                        .logoutUrl("/admin/logout") // 로그아웃 URL 설정
                        .logoutSuccessUrl("/admin/login/") // 로그아웃 성공 시 리다이렉트 URL 설정
                        .invalidateHttpSession(true) // 세션 무효화 설정
                        .deleteCookies("JSESSIONID") // 삭제할 쿠키 설정
                );
        return http.build();
    }
}
