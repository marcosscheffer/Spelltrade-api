package com.marcos.spelltrade.dto.storage;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record StorageCardRequestDto(
    @NotNull 
    UUID cardId,
    @NotNull 
    Integer quantity,
    @NotNull
    Boolean available
) {
    
}
