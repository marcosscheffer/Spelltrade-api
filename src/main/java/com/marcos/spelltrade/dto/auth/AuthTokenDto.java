package com.marcos.spelltrade.dto.auth;

public record AuthTokenDto(
    String accessToken,
    String refreshToken
) {
    
}
