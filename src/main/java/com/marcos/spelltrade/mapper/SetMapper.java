package com.marcos.spelltrade.mapper;

import org.mapstruct.Mapper;
import com.marcos.spelltrade.domain.entity.Set;
import com.marcos.spelltrade.dto.card.SetResponseDto;

@Mapper(componentModel = "spring")
public interface SetMapper {
    SetResponseDto toDto(Set entity);
}
