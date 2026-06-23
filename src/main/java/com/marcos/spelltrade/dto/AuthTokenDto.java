package com.marcos.spelltrade.dto;

public record AuthTokenDto(
    String accessToken,
    String refreshToken
) {
    
}
