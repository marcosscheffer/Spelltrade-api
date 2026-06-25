package com.marcos.spelltrade.dto.storage;

import java.time.Instant;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.marcos.spelltrade.domain.enums.Status;
import com.marcos.spelltrade.dto.common.ImageResponseDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StorageResponseDto(
    Long id,
    String name,
    Status status,
    ImageResponseDto image,
    Instant createdAt,
    Instant updatedAt,
    Boolean active
) {

}
