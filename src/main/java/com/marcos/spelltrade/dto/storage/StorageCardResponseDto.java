package com.marcos.spelltrade.dto.storage;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.marcos.spelltrade.dto.card.CardFaceResponseDto;
import com.marcos.spelltrade.dto.card.CardResponseDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StorageCardResponseDto(
    CardResponseDto card,
    List<CardFaceResponseDto> cardFace,
    Long storageId,
    Integer quantity,
    Boolean available,
    Instant createdAt,
    Instant updatedAt
) {
}
