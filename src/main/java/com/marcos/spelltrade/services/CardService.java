package com.marcos.spelltrade.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.marcos.spelltrade.domain.enums.Color;
import com.marcos.spelltrade.dto.card.CardResponseDto;
import com.marcos.spelltrade.mapper.CardMapper;
import com.marcos.spelltrade.repository.CardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public Page<CardResponseDto> getCards(
        String name,
        List<Color> colors,
        Boolean available,
        Pageable pageable
    ) {
        
        if (!Boolean.TRUE.equals(available)) 
            return cardRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(cardMapper::toDto);
        else if (colors == null) {
            return cardRepository.findAllCardsByNameAndAvailable(name, available, pageable)
                .map(cardMapper::toDto);
        } else {
            return cardRepository.findAllCardsByColors( 
                name, available, colors, colors.size(), pageable)
                .map(cardMapper::toDto);
        }
    }
}
