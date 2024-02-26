package kr.co.shoulf.web.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
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
                        .requestMatchers("/", "/login/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );
        http
                .csrf((auth) -> auth.disable());
        http
                .formLogin((auth) -> auth
                        .loginPage("/login/")
                        .loginProcessingUrl("/login/loginProc").permitAll()
                        .defaultSuccessUrl("/")
                );
        return http.build();
    }

}