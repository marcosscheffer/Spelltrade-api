package com.marcos.spelltrade.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityFilterChainConfig {
    private final JwtFilterConfig jwtFilterConfig;

    public SecurityFilterChainConfig(JwtFilterConfig jwtFilterConfig) {
        this.jwtFilterConfig = jwtFilterConfig;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(
        HttpSecurity http) throws Exception {
            return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                    session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                    ) 
                )
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers(
                        "/auth/register",
                        "/auth/login",
                        "/auth/refresh",
                        "/error"
                    )
                    .permitAll()
                    .requestMatchers("/admin/**")
                    .hasRole("ADMIN")
                    .anyRequest()
                    .authenticated()
                )
                .addFilterBefore(
                    jwtFilterConfig,
                    UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }
}
