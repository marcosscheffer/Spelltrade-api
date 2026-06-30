package com.marcos.spelltrade.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.marcos.spelltrade.domain.entity.Card;
import com.marcos.spelltrade.domain.enums.Color;

public interface CardRepository extends JpaRepository<Card, UUID> {
    @Query("""
            SELECT c FROM Card c
            JOIN c.storageCard sc
            JOIN c.colorIdentity ci
            WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))
            AND sc.available = :available
            AND sc.quantity > 0
            AND ci.color IN :colors
            GROUP BY c
            HAVING COUNT(DISTINCT ci.color) = :size
            """)
    Page<Card> findAllCardsByColors(
        String name, 
        Boolean available, 
        List<Color> colors, 
        int size, 
        Pageable pageable
    );

    @Query("""
            SELECT c FROM Card c
            JOIN c.storageCard sc
            WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))
            AND sc.available = :available
            AND sc.quantity > 0
            """)
    Page<Card> findAllCardsByNameAndAvailable(
        String name, 
        Boolean available, 
        Pageable pageable
    );

    Page<Card> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
