package com.marcos.spelltrade.mapper;

import org.mapstruct.Mapper;
import com.marcos.spelltrade.domain.entity.Card;
import com.marcos.spelltrade.dto.card.CardResponseDto;

@Mapper(
    componentModel = "spring",
    uses = {
        SetMapper.class,
        CardFaceMapper.class
    }
)
public interface CardMapper {
    CardResponseDto toDto(Card entity);
}
