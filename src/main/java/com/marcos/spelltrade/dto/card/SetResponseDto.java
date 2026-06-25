package com.marcos.spelltrade.dto.card;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.marcos.spelltrade.domain.enums.SetType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record SetResponseDto(
    UUID id,
    String code,
    String name,
    SetType type
) {

}
