package com.marcos.spelltrade.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRefreshDto(
    @NotBlank
    String refreshToken
) {
    
}
