package com.marcos.spelltrade.services;

import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.marcos.spelltrade.domain.entity.Card;
import com.marcos.spelltrade.domain.entity.Storage;
import com.marcos.spelltrade.domain.entity.StorageCard;
import com.marcos.spelltrade.domain.entity.StorageCardId;
import com.marcos.spelltrade.domain.entity.User;
import com.marcos.spelltrade.domain.enums.Status;
import com.marcos.spelltrade.dto.storage.StorageCardRequestDto;
import com.marcos.spelltrade.dto.storage.StorageCardResponseDto;
import com.marcos.spelltrade.exception.BusinessException;
import com.marcos.spelltrade.exception.ForbiddenException;
import com.marcos.spelltrade.mapper.StorageCardMapper;
import com.marcos.spelltrade.repository.StorageCardRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class StorageCardService {
    private final StorageService storageService;
    private final StorageCardRepository storageCardRepository;
    private final StorageCardMapper storageCardMapper;
    private final CardService cardService;

    public StorageCard getStorageCardById(StorageCardId storageCardId) {
        return storageCardRepository.findById(storageCardId)
            .orElseThrow(
                () -> new EntityNotFoundException("Not found card in storage")
            );
    }

    public Page<StorageCardResponseDto> getStorageCards(
        Long storageId, 
        User user, 
        String name, 
        Pageable pageable
    ) {
        Storage storage = storageService.getStorageById(storageId);

        if (storage.getStatus().equals(Status.PRIVATE)) {
            if (user == null) {
                throw new ForbiddenException("No permission to change this storage.");
            } else {
                storageService.verifyPermissionStorage(storage, user.getId());
            }
        }

        Page<StorageCardResponseDto> cards = storageCardRepository
            .findByStorageIdAndCardNameContainingIgnoreCase(storageId, name, pageable)
            .map(storageCardMapper::toDto);

        return cards;
    }

    public StorageCardResponseDto newCard(
        Long storageId, 
        StorageCardRequestDto dto, 
        Long userId
    ) {
        Storage storage = storageService.getStorageById(userId);
        
        storageService.verifyPermissionStorage(storage, userId);
        Card card = cardService.getCardById(dto.cardId());

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

    public StorageCardResponseDto changeStorageCard(
        Long storageId, 
        UUID cardId,
        StorageCardRequestDto dto, 
        User user
    ) {
            Storage storage = storageService.getStorageById(storageId);
            storageService.verifyPermissionStorage(storage, user.getId());

            StorageCardId storageCardId = new StorageCardId(cardId, storageId);
            StorageCard storageCard = getStorageCardById(storageCardId);

            if (dto.quantity() != null)
                storageCard.setQuantity(dto.quantity());
            if (dto.available() != null)
                storageCard.setAvailable(dto.available());

            storageCardRepository.save(storageCard);

            return storageCardMapper.toDto(storageCard);
    }

    public void deleteCard(Long storageId, UUID cardId, User user) {
        Storage storage = storageService.getStorageById(storageId);
        storageService.verifyPermissionStorage(storage, user.getId());
        StorageCardId id = new StorageCardId(cardId, storageId);
        storageCardRepository.deleteById(id);
    }
}
