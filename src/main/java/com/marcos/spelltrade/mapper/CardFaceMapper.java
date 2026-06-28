package com.marcos.spelltrade.mapper;

import org.mapstruct.Mapper;
import com.marcos.spelltrade.domain.entity.CardFace;
import com.marcos.spelltrade.dto.card.CardFaceResponseDto;

@Mapper(
    componentModel = "spring",
    uses = {
        CardImageMapper.class
    }
)
public interface CardFaceMapper {

    CardFaceResponseDto toDto(CardFace entity);
    
}
