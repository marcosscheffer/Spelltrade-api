package com.marcos.spelltrade.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.marcos.spelltrade.domain.entity.StorageCard;
import com.marcos.spelltrade.dto.storage.StorageCardRequestDto;
import com.marcos.spelltrade.dto.storage.StorageCardResponseDto;

@Mapper(
    componentModel = "spring",
    uses = { 
        CardMapper.class
    }
)
public interface StorageCardMapper {
    @Mapping(target = "storageId", source = "storage.id")
    StorageCardResponseDto toDto(StorageCard entity);

    @Mapping(target = "card", ignore = true)
    @Mapping(target = "storage", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "id", ignore = true)
    StorageCard toEntity(StorageCardRequestDto dto);
}
