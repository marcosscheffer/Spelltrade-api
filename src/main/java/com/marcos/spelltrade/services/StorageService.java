package com.marcos.spelltrade.services;

import org.springframework.stereotype.Service;
import com.marcos.spelltrade.domain.entity.Image;
import com.marcos.spelltrade.domain.entity.Storage;
import com.marcos.spelltrade.domain.entity.User;
import com.marcos.spelltrade.dto.CloudinaryUploadResultDto;
import com.marcos.spelltrade.dto.StorageRequestDto;
import com.marcos.spelltrade.dto.StorageResponseDto;
import com.marcos.spelltrade.mapper.ImageMapper;
import com.marcos.spelltrade.mapper.StorageMapper;
import com.marcos.spelltrade.repository.ImageRepository;
import com.marcos.spelltrade.repository.StorageRepository;
import com.marcos.spelltrade.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final StorageRepository storageRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final StorageMapper storageMapper;
    private final ImageMapper imageMapper;

    public StorageResponseDto getStorage(Long id) {
        Storage storage = storageRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Storage not found"));
        StorageResponseDto response = storageMapper.toDto(storage);
        return response;
    }

    public StorageResponseDto newStorage(StorageRequestDto dto, Long userId) {
        Storage storage = storageMapper.toEntity(dto);
        User user = userRepository.getReferenceById(userId);
        storage.setUser(user);
        storageRepository.save(storage);
        return storageMapper.toDto(storage);
    }

    public StorageResponseDto putImage(Long id, CloudinaryUploadResultDto dto) {
        Image image = imageMapper.toEntity(dto);
        imageRepository.save(image);

        Storage storage = storageRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Storage not found"));
        
        storage.setImage(image);
        storageRepository.save(storage);
        return storageMapper.toDto(storage);
    }

}
