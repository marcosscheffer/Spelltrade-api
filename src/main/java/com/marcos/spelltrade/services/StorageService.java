package com.marcos.spelltrade.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.marcos.spelltrade.domain.entity.Card;
import com.marcos.spelltrade.domain.entity.Image;
import com.marcos.spelltrade.domain.entity.Storage;
import com.marcos.spelltrade.domain.entity.StorageCard;
import com.marcos.spelltrade.domain.entity.User;
import com.marcos.spelltrade.domain.enums.Status;
import com.marcos.spelltrade.dto.storage.StorageCardRequestDto;
import com.marcos.spelltrade.dto.storage.StorageCardResponseDto;
import com.marcos.spelltrade.dto.storage.StorageRequestDto;
import com.marcos.spelltrade.dto.storage.StorageResponseDto;
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


    private void verifyUserStorage(Long storageId, Long userId, Status status) {
        // status public verifies user to modify storage
        // status private verifies user to get storage
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Storage storage = storageRepository.findById(storageId)
            .orElseThrow(() -> new EntityNotFoundException("Storage not found"));

        if (storage.getStatus().equals(status)) {
                if (user.getId().equals(userId) 
                    && employeeRepository.existsByUserIdAndStorageId(userId, storageId)) {
                        throw new ForbiddenException("No permission to change this storage.");
                }
            }
    }

    public List<StorageResponseDto> getStorages() {
        List<StorageResponseDto>response = storageRepository.findAll().stream()
            .filter(storage -> storage.getStatus().equals(Status.PUBLIC))
            .map(storage -> storageMapper.toDto(storage))
            .toList();
        
        return response;
    }

    public StorageResponseDto getStorage(Long storageId, Long userId) {
        verifyUserStorage(storageId, userId, Status.PRIVATE);

        Storage storage = storageRepository.findById(storageId)
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

    public StorageResponseDto putImage(Long id, Image image, Long userId) {
        verifyUserStorage(id, userId, Status.PUBLIC);

        Storage storage = storageRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Storage not found"));
        
        storage.setImage(image);
        storageRepository.save(storage);
        return storageMapper.toDto(storage);
    }

    public List<StorageCardResponseDto> getCardsStorage(Long id) {
        List<StorageCardResponseDto> cards = storageCardRepository.findAll().stream()
            .filter(card -> card.getStorage().getId().equals(id))
            .map(card -> storageCardMapper.toDto(card))
            .toList();

        return cards;
    }

    public StorageCardResponseDto newCard(
        Long storageId, StorageCardRequestDto dto, Long userId) {
            verifyUserStorage(storageId, userId, Status.PRIVATE);

            Storage storage = storageRepository.findById(storageId)
                .orElseThrow(() -> new EntityNotFoundException("Storage not found"));

            Card card = cardRepository.findById(dto.cardId())
                .orElseThrow(() -> new EntityNotFoundException("Card not found"));

            StorageCard response = storageCardMapper.toEntity(dto);
            response.setCard(card);
            response.setStorage(storage);

            storageCardRepository.save(response);

            return storageCardMapper.toDto(response);
    }
}
