package com.marcos.spelltrade.controller;

import org.springframework.web.bind.annotation.RestController;
import com.marcos.spelltrade.dto.auth.AuthLoginDto;
import com.marcos.spelltrade.dto.auth.AuthRefreshDto;
import com.marcos.spelltrade.dto.auth.AuthRegisterDto;
import com.marcos.spelltrade.dto.auth.AuthTokenDto;
import com.marcos.spelltrade.dto.auth.AuthUserDto;
import com.marcos.spelltrade.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthTokenDto> login(@RequestBody @Valid AuthLoginDto dto) {
        AuthTokenDto token = authService.login(dto);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid AuthRegisterDto dto) {
        AuthUserDto user = authService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthTokenDto> refresh(@RequestBody @Valid AuthRefreshDto dto) {
        AuthTokenDto token = authService.refresh(dto);
        return ResponseEntity.ok(token);
    }
}
