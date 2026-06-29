package com.marcos.spelltrade.controller;

import org.springframework.web.bind.annotation.RestController;
import com.marcos.spelltrade.dto.card.CardResponseDto;
import com.marcos.spelltrade.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping
    public ResponseEntity<Page<CardResponseDto>> getcards(Pageable pageable) {
        Page<CardResponseDto> response = cardService.getCards(pageable);
        return ResponseEntity.ok(response);
    }
    
}
