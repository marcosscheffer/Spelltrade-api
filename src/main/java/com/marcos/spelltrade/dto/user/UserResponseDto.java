package com.marcos.spelltrade.dto.user;

import java.time.Instant;

public record UserResponseDto(
    Long id,
    String name,
    String email,
    String phone,
    Instant createdAt,
    Instant updatedAt,
    Boolean active
) {
    
}
