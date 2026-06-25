package com.marcos.spelltrade.services;

import org.springframework.stereotype.Service;
import com.marcos.spelltrade.domain.entity.Image;
import com.marcos.spelltrade.dto.common.CloudinaryUploadResultDto;
import com.marcos.spelltrade.mapper.ImageMapper;
import com.marcos.spelltrade.repository.ImageRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    public Image saveImage(CloudinaryUploadResultDto dto) {
        Image image = imageMapper.toEntity(dto);
        imageRepository.save(image);
        return image;
    }
}
