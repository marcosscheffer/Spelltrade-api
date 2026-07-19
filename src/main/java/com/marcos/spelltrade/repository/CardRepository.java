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
            """)
    Page<Card> findAllCardsAvailable(
        String name, 
        Boolean available,
        Pageable pageable
    );

    @Query("""
            SELECT c FROM Card c
            JOIN c.storageCard sc
            JOIN c.colorIdentity ci
            WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))
            AND sc.available = :available
            AND ci.color IN :colors
            GROUP BY c
            HAVING COUNT(DISTINCT ci.color) = :size
            """)
    Page<Card> findAllCardsAvailableByColors(
        String name, 
        Boolean available,
        Integer size,
        List<Color> colors, 
        Pageable pageable
    );

    @Query("""
            SELECT c FROM Card c
            JOIN c.colorIdentity ci
            WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))
            AND ci.color IN :colors
            GROUP BY c
            HAVING COUNT(DISTINCT ci.color) = :size
            """)
    Page<Card> findAllCardsByColors(
        String name, 
        List<Color> colors, 
        Integer size,
        Pageable pageable
    );

    @Query("""
            SELECT c FROM Card c
            WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    Page<Card> findAllCards(
        String name, 
        Pageable pageable
    );

}
