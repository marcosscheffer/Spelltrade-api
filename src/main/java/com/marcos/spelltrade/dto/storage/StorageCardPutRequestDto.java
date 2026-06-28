package com.marcos.spelltrade.dto.storage;

public record StorageCardPutRequestDto(
    Boolean available,
    Integer quantity
) {
}
