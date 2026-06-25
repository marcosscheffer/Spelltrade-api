package com.marcos.spelltrade.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRegisterDto(
    @NotBlank
    String name,
    @NotBlank
    @Size(min = 8, max = 100)
    String password,
    @NotBlank
    @Email
    String email,
    @NotBlank
    @Size(min = 8, max=25)
    String phone
) {    
}
