package com.marcos.spelltrade.services;

import java.util.UUID;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.marcos.spelltrade.domain.entity.Card;
import com.marcos.spelltrade.domain.entity.Image;
import com.marcos.spelltrade.domain.entity.Storage;
import com.marcos.spelltrade.domain.entity.StorageCard;
import com.marcos.spelltrade.domain.entity.StorageCardId;
import com.marcos.spelltrade.domain.entity.User;
import com.marcos.spelltrade.domain.enums.Status;
import com.marcos.spelltrade.dto.storage.StorageCardPutRequestDto;
import com.marcos.spelltrade.dto.storage.StorageCardRequestDto;
import com.marcos.spelltrade.dto.storage.StorageCardResponseDto;
import com.marcos.spelltrade.dto.storage.StorageRequestDto;
import com.marcos.spelltrade.dto.storage.StorageResponseDto;
import com.marcos.spelltrade.exception.BusinessException;
import com.marcos.spelltrade.exception.ForbiddenException;
import com.marcos.spelltrade.mapper.StorageCardMapper;
import com.marcos.spelltrade.mapper.StorageMapper;
import com.marcos.spelltrade.repository.CardRepository;
import com.marcos.spelltrade.repository.EmployeeRepository;
import com.marcos.spelltrade.repository.StorageCardRepository;
import com.marcos.spelltrade.repository.StorageRepository;
import com.marcos.spelltrade.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final StorageRepository storageRepository;
    private final UserRepository userRepository;
    private final StorageCardRepository storageCardRepository;
    private final CardRepository cardRepository;
    private final EmployeeRepository employeeRepository;

    private final StorageMapper storageMapper;
    private final StorageCardMapper storageCardMapper;


    private void verifyPermissionStorage(Storage storage, Long userId) {
        // Verify if user is owner or employee of storage for modify
        if (!storage.getUser().getId().equals(userId) 
            && !employeeRepository.existsByUserIdAndStorageId(userId, storage.getId())) {
                throw new ForbiddenException("No permission to change this storage.");
        }
    }

    public Page<StorageResponseDto> getStorages(String name, Pageable pageable) {
        Page<StorageResponseDto> response = storageRepository
            .findByStatusAndNameContainingIgnoreCase(Status.PUBLIC, name, pageable)
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
        User user = userRepository.getReferenceById(userId);
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

    public Page<StorageCardResponseDto> getCardsStorage(
        Long storageId, User user, String name, Pageable pageable) {
            Storage storage = storageRepository.findById(storageId)
                .orElseThrow(() -> new EntityNotFoundException("Storage not found"));

            if (storage.getStatus().equals(Status.PRIVATE)) {
                if (user == null) {
                    throw new ForbiddenException("No permission to change this storage.");
                } else {
                    verifyPermissionStorage(storage, user.getId());
                }
            }

            Page<StorageCardResponseDto> cards = storageCardRepository
                .findByStorageIdAndCardNameContainingIgnoreCase(storageId, name, pageable)
                .map(storageCardMapper::toDto);

            return cards;
    }

    public StorageCardResponseDto newCard(
        Long storageId, StorageCardRequestDto dto, Long userId) {
            Storage storage = storageRepository.findById(storageId)
                .orElseThrow(() -> new EntityNotFoundException("Storage not found"));
            
            verifyPermissionStorage(storage, userId);
            Card card = cardRepository.findById(dto.cardId())
                .orElseThrow(() -> new EntityNotFoundException("Card not found"));

            StorageCard response = storageCardMapper.toEntity(dto);
            response.setCard(card);
            response.setStorage(storage);

            try {
                storageCardRepository.save(response);
            } catch(DataIntegrityViolationException exception) {
                throw new BusinessException("Card already exists in this storage");
            } 

            return storageCardMapper.toDto(response);
    }

    public StorageCardResponseDto changeStorageCard(Long storageId, UUID cardId,
        StorageCardPutRequestDto dto, User user) {
            Storage storage = storageRepository.findById(storageId)
                .orElseThrow(() -> new EntityNotFoundException("Storage not found"));

            verifyPermissionStorage(storage, user.getId());

            StorageCardId id = new StorageCardId(cardId, storageId);
            StorageCard storageCard = storageCardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card not found"));

            if (dto.quantity() != null)
                storageCard.setQuantity(dto.quantity());
            if (dto.available() != null)
                storageCard.setAvailable(dto.available());

            storageCardRepository.save(storageCard);

            return storageCardMapper.toDto(storageCard);
    }

    public void deleteCard(Long storageId, UUID cardId, User user) {
        Storage storage = storageRepository.findById(storageId)
            .orElseThrow(() -> new EntityNotFoundException("Storage not found"));

        verifyPermissionStorage(storage, user.getId());
        StorageCardId id = new StorageCardId(cardId, storageId);
        storageCardRepository.deleteById(id);
    }
}
