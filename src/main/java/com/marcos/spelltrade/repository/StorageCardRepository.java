package com.marcos.spelltrade.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.marcos.spelltrade.domain.entity.StorageCard;
import com.marcos.spelltrade.domain.entity.StorageCardId;

public interface StorageCardRepository extends JpaRepository<StorageCard, StorageCardId>{
    Page<StorageCard> findByStorageIdAndCardNameContainingIgnoreCase(
        Long storageId,
        String name,
        Pageable pageable
    );
}
