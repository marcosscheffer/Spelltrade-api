package com.marcos.spelltrade.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.marcos.spelltrade.domain.entity.User;
import com.marcos.spelltrade.dto.storage.StorageCardRequestDto;
import com.marcos.spelltrade.dto.storage.StorageCardResponseDto;
import com.marcos.spelltrade.services.StorageCardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/storages/{id}/cards")
@RequiredArgsConstructor 
public class StorageCardController {
    private final StorageCardService storageCardService;

    @GetMapping
    public ResponseEntity<Page<StorageCardResponseDto>> getCardsStorage(
        Pageable pageable,
        @RequestParam(defaultValue = "") String q,
        @PathVariable Long storageId,
        @AuthenticationPrincipal User principal
    ) {
        Page<StorageCardResponseDto> response = storageCardService.getStorageCards(storageId, principal, q, pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<StorageCardResponseDto> newCard (
        @PathVariable Long storageId,
        @RequestBody @Valid StorageCardRequestDto dto,
        @AuthenticationPrincipal User principal,
        UriComponentsBuilder uri
    ) {
        StorageCardResponseDto card = storageCardService.newCard(storageId, dto, principal.getId());
        return ResponseEntity.ok(card);
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<StorageCardResponseDto> changeStorageCard(
        @PathVariable Long storageId, 
        @PathVariable UUID cardId, 
        @RequestBody StorageCardRequestDto dto,
        @AuthenticationPrincipal User principal
    ) {
        StorageCardResponseDto response = storageCardService.changeStorageCard(storageId, cardId, dto, principal);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<Void> deleteCard(
        @PathVariable Long storageId,
        @PathVariable UUID cardId,
        @AuthenticationPrincipal User principal
    ) {
        storageCardService.deleteCard(storageId, cardId, principal);
        return ResponseEntity.noContent().build();
    }
}
