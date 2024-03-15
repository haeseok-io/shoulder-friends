package kr.co.shoulf.web.security;

import kr.co.shoulf.web.security.auth.CustomOAuth2UserService;
import kr.co.shoulf.web.security.custom.userDetails.service.CustomMemberUserDetailsService;
import kr.co.shoulf.web.security.custom.userDetails.service.CustomUserDetailsService;
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
public class SecurityConfig {
    private final CustomMemberUserDetailsService customMemberUserDetailsService;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public UserDetailsService userMemberDetailsService() {
        return customMemberUserDetailsService;
    }

    @Bean
    public PasswordEncoder memberPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public DaoAuthenticationProvider memberDaoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userMemberDetailsService());
        provider.setPasswordEncoder(memberPasswordEncoder());

        return provider;
    }

//    @Bean
    public DaoAuthenticationProvider userDaoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
        log.info("----- 시큐리티 적용2 -----");
        http
                .securityMatcher("/login/**", "/oauth2/**", "/logout", "/moim/**",
                        "/board/**", "/meeting/**", "/message/**", "/alarm/**")
                // CSRF 보안 설정 비활성화
                .csrf((csrf)->csrf.disable())
                .authenticationProvider(userDaoAuthenticationProvider())
                // 로그인 설정
                .formLogin((auth) -> auth
                        .loginPage("/login") // 로그인 페이지 설정
                        .loginProcessingUrl("/login/loginProc") // 로그인 처리 URL 설정
                        .failureUrl("/login?error") // 로그인 실패 시 이동할 URL 설정
                        .defaultSuccessUrl("/")
                )
                // 권한 설정
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/login/**","/oauth2/**").permitAll()
                        .requestMatchers("/mypage/**", "/moim/write", "/moimLike/**", "/message",
                                "/board/write", "/board/modify", "/board/detail", "/meeting/**",
                                "/alarm/list").hasRole("USER")
                        .anyRequest().permitAll()
                )
                // Oauth2 로그인 설정
                .oauth2Login(
                        (oauth2Login) -> oauth2Login
                                .loginPage("/login") // 로그인 페이지 설정
                                .defaultSuccessUrl("/", true)
                                .userInfoEndpoint(userInfo -> // 사용자 정보 엔드포인트 설정
                                        userInfo.userService(customOAuth2UserService) // 사용자 정보 서비스 지정
                                )
                )
                //로그아웃 설정
                .logout((logout) -> logout
                        .logoutUrl("/logout") // 로그아웃 URL 설정
                        .logoutSuccessUrl("/") // 로그아웃 성공 시 리다이렉트 URL 설정
                        .invalidateHttpSession(true) // 세션 무효화 설정
                        .deleteCookies("JSESSIONID") // 삭제할 쿠키 설정
                );


        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
        log.info("----- 시큐리티 적용1 -----");
        http
                // CSRF 보안 설정 비활성화
                .securityMatcher("/admin/**", "/member/**", "/commute/**",
                        "/annual/**","/study/**", "/reservation/**")
                .csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(memberDaoAuthenticationProvider())
                // 권한 설정
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/admin/login/**","/admin/loginProc").permitAll()
                        .requestMatchers("/admin/**", "/member/**", "/commute/**",
                                "/annual/**","/study/**", "/reservation/**").hasAnyRole("ADMIN","EMP") // admin 및 emp 역할이 있는 사용자만 해당 URL에 접근 가능
                        .anyRequest().authenticated() // 그 외의 요청은 인증된 사용자만
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
