package com.marcos.spelltrade.mapper;

import org.mapstruct.Mapper;
import com.marcos.spelltrade.domain.entity.ColorIdentity;
import com.marcos.spelltrade.domain.enums.Color;

@Mapper(componentModel = "spring")
public interface ColorIdentityMapper {
    default Color toColor(ColorIdentity color) {
        return color.getColor();
    }
}
