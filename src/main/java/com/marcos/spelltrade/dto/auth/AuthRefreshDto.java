package com.marcos.spelltrade.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthRefreshDto(
    @NotBlank
    String refreshToken
) {
    
}
