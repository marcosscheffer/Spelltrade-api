package com.marcos.spelltrade.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import com.marcos.spelltrade.domain.entity.Image;
import com.marcos.spelltrade.domain.entity.User;
import com.marcos.spelltrade.dto.common.CloudinaryUploadResultDto;
import com.marcos.spelltrade.dto.storage.StorageCardRequestDto;
import com.marcos.spelltrade.dto.storage.StorageCardResponseDto;
import com.marcos.spelltrade.dto.storage.StorageRequestDto;
import com.marcos.spelltrade.dto.storage.StorageResponseDto;
import com.marcos.spelltrade.services.CloudinaryService;
import com.marcos.spelltrade.services.ImageService;
import com.marcos.spelltrade.services.StorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/storages")
@RequiredArgsConstructor
public class StorageController {
    private final CloudinaryService cloudinaryService;
    private final StorageService storageService;
    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<StorageResponseDto> newStorage(
        @RequestBody @Valid StorageRequestDto dto,
        @AuthenticationPrincipal User principal,
        UriComponentsBuilder uriBuilder
    ) {
        StorageResponseDto response = storageService.newStorage(dto, principal.getId());
        URI uri = uriBuilder.path("/storages/{id}").buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<List<StorageResponseDto>> getStorages() {
        List<StorageResponseDto> response = storageService.getStorages();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StorageResponseDto> getStorage(
        @PathVariable Long id,
        @AuthenticationPrincipal User principal
    ) {
        StorageResponseDto response = storageService.getStorage(id, principal.getId());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/upload-image")
    public ResponseEntity<StorageResponseDto> uploadImageStorage(
        @PathVariable Long id,
        @RequestParam MultipartFile file,
        @AuthenticationPrincipal User principal
    ) {
        CloudinaryUploadResultDto uploadResult = cloudinaryService.upload(file);
        Image image = imageService.saveImage(uploadResult);
        StorageResponseDto response = storageService.putImage(
            id, image, principal.getId());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/cards")
    public ResponseEntity<List<StorageCardResponseDto>> getCardsStorage(
        @PathVariable Long id,
        @AuthenticationPrincipal User principal
    ) {
        List<StorageCardResponseDto> response = storageService.getCardsStorage(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/cards")
    public ResponseEntity<StorageCardResponseDto> newCard (
        @PathVariable Long id,
        @RequestBody @Valid StorageCardRequestDto dto,
        @AuthenticationPrincipal User principal,
        UriComponentsBuilder uri
    ) {
        StorageCardResponseDto card = storageService.newCard(id, dto, principal.getId());
        
        return ResponseEntity.ok(card);
        
    }
    
}
