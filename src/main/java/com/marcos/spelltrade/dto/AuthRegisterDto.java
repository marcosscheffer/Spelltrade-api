package com.marcos.spelltrade.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRegisterDto(
    @NotBlank
    String name,
    @NotBlank
    @Size(min = 8, max = 100, message = "Password must be longer than 8 characters.")
    String password,
    @NotBlank
    @Email
    String email
) {    
}
