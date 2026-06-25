package com.marcos.spelltrade.dto.card;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CardImageResponseDto(
    Short faceIndex,
    String small,
    String normal,
    String large,
    String borderCrop,
    String artCrop
) {
    
}
