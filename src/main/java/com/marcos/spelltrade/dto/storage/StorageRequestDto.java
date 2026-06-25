package com.marcos.spelltrade.dto.storage;

import jakarta.validation.constraints.NotBlank;

public record StorageRequestDto(
    @NotBlank
    String name,
    @NotBlank
    String status
) {
    
}
