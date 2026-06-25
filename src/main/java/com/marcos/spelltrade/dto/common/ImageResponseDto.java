package com.marcos.spelltrade.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ImageResponseDto(
    Long id,
    String imageUrl
) {

}
