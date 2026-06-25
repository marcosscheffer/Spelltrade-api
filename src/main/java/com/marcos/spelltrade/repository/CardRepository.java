package com.marcos.spelltrade.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.marcos.spelltrade.domain.entity.Card;

public interface CardRepository extends JpaRepository<Card, UUID> {
    
}
