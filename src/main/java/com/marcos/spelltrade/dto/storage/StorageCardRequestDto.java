package com.marcos.spelltrade.dto.storage;

import java.util.UUID;

public record StorageCardRequestDto(
    UUID cardId,
    Integer quantity,
    Boolean available
) {
    
}
