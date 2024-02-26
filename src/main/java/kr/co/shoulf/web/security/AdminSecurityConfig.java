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
                        .requestMatchers("/admin/**").hasAnyRole("admin","emp")
                        .anyRequest().permitAll()
                );
        http
                .formLogin((auth) -> auth
                        .loginPage("/admin/login/")
                        .loginProcessingUrl("/admin/loginProc").permitAll()
                        .defaultSuccessUrl("/admin/")
                );
        http
                .csrf((auth) -> auth.disable());
        return http.build();
    }
}
