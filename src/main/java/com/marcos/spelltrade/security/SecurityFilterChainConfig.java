package com.marcos.spelltrade.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityFilterChainConfig {
    private final JwtFilterConfig jwtFilterConfig;

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


                .exceptionHandling(ex -> ex
                    .authenticationEntryPoint((request, response, authException) -> {
                        response.sendError(
                            HttpServletResponse.SC_UNAUTHORIZED,
                            "Unauthorized"
                        );
                    })
                )

                .authorizeHttpRequests(auth -> auth
                    // Endpoints of authentication
                    .requestMatchers(
                        "/auth/register",
                        "/auth/login",
                        "/auth/refresh",
                        "/error"
                    )
                    .permitAll()
                    // Endpoints of '/storages'
                    .requestMatchers(
                        HttpMethod.GET,
                        "/storages",
                        "/storages/{id}",
                        "/storages/{id}/cards"
                    )
                    .permitAll()
                    // Endpoints of '/cards'
                    .requestMatchers(
                        HttpMethod.GET,
                        "/cards",
                        "/cards/{id}"
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
