package com.marcos.spelltrade.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.marcos.spelltrade.domain.entity.Image;
import com.marcos.spelltrade.domain.entity.Storage;
import com.marcos.spelltrade.domain.entity.User;
import com.marcos.spelltrade.domain.enums.Status;
import com.marcos.spelltrade.dto.storage.StorageRequestDto;
import com.marcos.spelltrade.dto.storage.StorageResponseDto;
import com.marcos.spelltrade.exception.ForbiddenException;
import com.marcos.spelltrade.mapper.StorageMapper;
import com.marcos.spelltrade.repository.StorageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final StorageRepository storageRepository;
    private final StorageMapper storageMapper;
    private final UserService userService;

    public void verifyPermissionStorage(Storage storage, Long userId) {
        if (!storage.getUser().getId().equals(userId)) {
                throw new ForbiddenException("No permission to change this storage.");
        }
    }

    public Storage getStorageById(Long id) {
        return storageRepository.findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Storage not found")
            );
    }

    public Page<StorageResponseDto> getStorages(String name, Pageable pageable) {
        Page<StorageResponseDto> response = storageRepository
            .findByStatusAndNameContainingIgnoreCase(Status.PUBLIC, name, pageable)
            .map(storageMapper::toDto);
        
        return response;
    }

    public Page<StorageResponseDto> getMyStorages(String name, Long userId, Pageable pageable) {
        Page<StorageResponseDto> response = storageRepository
            .findByUserIdAndNameContainingIgnoreCase(userId, name, pageable)
            .map(storageMapper::toDto);
        
        return response;
    }

    public StorageResponseDto getStorage(Long storageId, User user) {
        Storage storage = storageRepository.findById(storageId)
            .orElseThrow(() -> new EntityNotFoundException("Storage not found"));
        
        if (storage.getStatus().equals(Status.PRIVATE)) {
            if (user == null) {
                throw new ForbiddenException("No permission to change this storage.");
            } else {
                verifyPermissionStorage(storage, user.getId());
            }
        }

        StorageResponseDto response = storageMapper.toDto(storage);
        return response;
    }

    public StorageResponseDto newStorage(StorageRequestDto dto, Long userId) {
        Storage storage = storageMapper.toEntity(dto);
        User user = userService.getUserById(userId);
        storage.setUser(user);
        storageRepository.save(storage);
        return storageMapper.toDto(storage);
    }

    public StorageResponseDto putImage(Long id, Image image, Long userId) {
        Storage storage = storageRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Storage not found"));

        verifyPermissionStorage(storage, userId);

        storage.setImage(image);
        storageRepository.save(storage);
        return storageMapper.toDto(storage);
    }

    public void deleteStorage(Long id, User user) {
        Storage storage = storageRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Storage not found"));
        verifyPermissionStorage(storage, user.getId());
        storageRepository.deleteById(id);
    }
}
