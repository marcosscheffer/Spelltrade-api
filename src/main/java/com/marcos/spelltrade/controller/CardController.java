package com.marcos.spelltrade.controller;

import org.springframework.web.bind.annotation.RestController;
import com.marcos.spelltrade.domain.enums.Color;
import com.marcos.spelltrade.dto.card.CardResponseDto;
import com.marcos.spelltrade.services.CardService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping
    public ResponseEntity<Page<CardResponseDto>> getcards(
        @RequestParam(defaultValue = "") String q,
        @RequestParam(required = false) List<Color> colors,
        @RequestParam(required = false) Boolean available,
        Pageable pageable
    ) {
        Page<CardResponseDto> response = cardService.getCards(
            q, colors, available, pageable);
        return ResponseEntity.ok(response);
    }
    
}
