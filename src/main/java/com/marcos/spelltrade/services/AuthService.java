package com.marcos.spelltrade.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.marcos.spelltrade.domain.entity.User;
import com.marcos.spelltrade.domain.enums.Roles;
import com.marcos.spelltrade.dto.AuthLoginDto;
import com.marcos.spelltrade.dto.AuthRefreshDto;
import com.marcos.spelltrade.dto.AuthRegisterDto;
import com.marcos.spelltrade.dto.AuthTokenDto;
import com.marcos.spelltrade.dto.AuthUserDto;
import com.marcos.spelltrade.mapper.UserMapper;
import com.marcos.spelltrade.repository.UserRepository;
import com.marcos.spelltrade.security.JwtService;

@Service
public class AuthService {
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthService(UserMapper userMapper, PasswordEncoder encoder, 
        UserRepository repository, AuthenticationManager authenticationManager, 
        JwtService jwtService, UserService userService) {
        this.userMapper = userMapper;
        this.encoder = encoder;
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    public AuthUserDto register(AuthRegisterDto dto) {
        User user = userMapper.toEntity(dto);
        user.setPassword(
            encoder.encode(dto.password())
        );
        user.setRole(Roles.USER);
        repository.save(user);

        return userMapper.toDto(user);
    }

    public AuthTokenDto login(AuthLoginDto dto) {
        Authentication authentication = 
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    dto.email(), 
                    dto.password())
            );
        
        UserDetails user = (UserDetails) authentication.getPrincipal();

        AuthTokenDto response = new AuthTokenDto(
            jwtService.generateAccessToken(user),
            jwtService.generateRefreshToken(user)
        );
        return response;
    }

    public AuthTokenDto refresh(AuthRefreshDto dto) {
        String email = jwtService.extractSubject(dto.refreshToken());

        UserDetails user = userService.loadUserByUsername(email);

        return new AuthTokenDto(
            jwtService.generateAccessToken(user), dto.refreshToken());
    }
}
