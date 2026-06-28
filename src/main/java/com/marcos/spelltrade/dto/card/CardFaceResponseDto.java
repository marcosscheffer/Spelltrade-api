package com.marcos.spelltrade.dto.card;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record CardFaceResponseDto(
    Long id,
    Short faceIndex,
    String name,
    String manaCost,
    String typeLine,
    String oracleText,
    String power,
    String toughness,
    String loyalty,
    CardImageResponseDto cardImage
) {

}
