package com.marcos.spelltrade.dto;

import java.time.Instant;
import com.marcos.spelltrade.domain.enums.Status;

public record StorageResponseDto(
    Long id,
    String name,
    Status status,
    String image,
    Instant createdAt,
    Instant updatedAt,
    Boolean active
) {

}
