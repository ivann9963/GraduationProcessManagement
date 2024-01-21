package com.project.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

//    private final AuthenticationManager authenticationManager;
//    public SecurityConfig(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }

    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .addFilterBefore(new CustomHeaderAuthenticationFilter(authenticationManager),
//                        UsernamePasswordAuthenticationFilter.class)
//                .userDetailsService(userDetailsService)

                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/api/register", "/api/login").permitAll()
//                        .requestMatchers("/api/thesis/upload-thesis").hasRole("STUDENT")
                        .requestMatchers("/api/thesis/upload-thesis").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin()
                .loginProcessingUrl("/api/login")  // handle login attempt
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/api/logout")  // handle logout
                .permitAll()
                .and()
                .formLogin().disable() // Disable form login
                .httpBasic().disable() // Disable HTTP basic authentication
                .csrf().disable();  // CSRF is handled via the session mechanism

        return http.build();
    }


//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        // Configure and return the built-in authentication manager
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }
}
