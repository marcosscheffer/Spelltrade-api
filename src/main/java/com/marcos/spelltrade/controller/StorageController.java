package com.marcos.spelltrade.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import com.marcos.spelltrade.domain.entity.User;
import com.marcos.spelltrade.dto.CloudinaryUploadResultDto;
import com.marcos.spelltrade.dto.StorageRequestDto;
import com.marcos.spelltrade.dto.StorageResponseDto;
import com.marcos.spelltrade.services.CloudinaryService;
import com.marcos.spelltrade.services.StorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.net.URI;
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

    @GetMapping
    public String getStorages(@RequestParam String param) {
        return new String();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StorageResponseDto> getStorage(@PathVariable Long id) {
        StorageResponseDto response = storageService.getStorage(id);
        return ResponseEntity.ok(response);
    }

    

    @PutMapping("/{id}/upload-image")
    public ResponseEntity<StorageResponseDto> uploadImageStorage(
        @PathVariable Long id,
        @RequestParam MultipartFile file
    ) {
        CloudinaryUploadResultDto image = cloudinaryService.upload(file);
        StorageResponseDto response = storageService.putImage(id ,image);

        return ResponseEntity.ok(response);
    }

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
}
