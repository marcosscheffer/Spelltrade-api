package com.marcos.spelltrade.mapper;

import org.mapstruct.Mapper;
import com.marcos.spelltrade.domain.entity.CardImage;
import com.marcos.spelltrade.dto.card.CardImageResponseDto;

@Mapper(componentModel = "spring")
public interface CardImageMapper {
    CardImageResponseDto toDto(CardImage entity);
}
