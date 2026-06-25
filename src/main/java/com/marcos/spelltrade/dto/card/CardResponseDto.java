package com.marcos.spelltrade.dto.card;

import java.time.LocalDate;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.marcos.spelltrade.domain.enums.RarityType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CardResponseDto(
    UUID id,
    UUID oracleId,
    String name,
    String lang,
    String power,
    String toughness,
    String loyalty,
    String manaCost,
    String oracleText,
    LocalDate releasedAt,
    Boolean reserved,
    RarityType rarity,
    String typeLine,
    SetResponseDto set,
    CardImageResponseDto image
) {
    
}
