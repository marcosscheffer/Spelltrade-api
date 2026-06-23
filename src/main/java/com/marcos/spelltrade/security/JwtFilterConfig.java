package com.marcos.spelltrade.security;

import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.marcos.spelltrade.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilterConfig extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserService userService;

    public JwtFilterConfig(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        
        String token = request.getHeader("Authorization");

        if(token == null || !token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = token.replace("Bearer", "");

        String email = jwtService.extractSubject(token);
        UserDetails user = userService.loadUserByUsername(email);

        UsernamePasswordAuthenticationToken auth = 
            new UsernamePasswordAuthenticationToken(
                user, 
                null, 
                user.getAuthorities());
        
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);
    }
}
