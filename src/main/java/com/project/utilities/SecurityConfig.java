package com.project.utilities;

import com.project.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/api/register").permitAll()
                        .requestMatchers("/api/login").permitAll()
                        .requestMatchers("/teachers/**").hasRole("TEACHER")
                        .requestMatchers("/students/**").hasRole("STUDENT")
                        .anyRequest().authenticated()
                )
                .formLogin().disable() // Disable the default form login page
                .httpBasic().disable() // Disable basic authentication
                .csrf().disable() // If you are using a REST API, you might want to disable CSRF protection
                // Other configurations such as CORS, headers, etc.
                .logout().disable(); // You might also want to disable logout if you handle it in the front-end
//                .formLogin()
//                .and()
//                .logout();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
