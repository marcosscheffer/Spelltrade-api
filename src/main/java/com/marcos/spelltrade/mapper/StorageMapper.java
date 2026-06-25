package com.marcos.spelltrade.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.marcos.spelltrade.domain.entity.Storage;
import com.marcos.spelltrade.domain.enums.Status;
import com.marcos.spelltrade.dto.storage.StorageRequestDto;
import com.marcos.spelltrade.dto.storage.StorageResponseDto;

@Mapper(componentModel = "spring")
public interface StorageMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "active", ignore = true)
    Storage toEntity(StorageRequestDto dto);

    StorageResponseDto toDto(Storage entity);

    default Status mapStatus(String status) {
        try {
            return Status.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException exception) {
            return Status.PRIVATE;
        }
    }
}
