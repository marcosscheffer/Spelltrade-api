package com.marcos.spelltrade.dto.auth;

import java.time.Instant;

public record AuthUserDto(
    String name,
    String email,
    String role,
    Instant createdAt,
    Instant updatedAt
) {

}
