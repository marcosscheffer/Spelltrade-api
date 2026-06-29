package com.marcos.spelltrade.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.marcos.spelltrade.dto.card.CardResponseDto;
import com.marcos.spelltrade.mapper.CardMapper;
import com.marcos.spelltrade.repository.CardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public Page<CardResponseDto> getCards(Pageable pageable) {
        return cardRepository.findAllByOrderByNameAsc(pageable).map(cardMapper::toDto);
    }
}
