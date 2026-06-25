package com.marcos.spelltrade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.marcos.spelltrade.domain.entity.StorageCard;
import com.marcos.spelltrade.domain.entity.StorageCardId;

public interface StorageCardRepository extends JpaRepository<StorageCard, StorageCardId>{
    
}
