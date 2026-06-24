package com.marcos.spelltrade.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.marcos.spelltrade.domain.entity.Image;
import com.marcos.spelltrade.dto.CloudinaryUploadResultDto;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    @Mapping(target = "id", ignore = true)
    Image toEntity(CloudinaryUploadResultDto dto);
    
}
