package com.marcos.spelltrade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.marcos.spelltrade.domain.entity.Storage;

public interface StorageRepository extends JpaRepository<Storage, Long> {
    
}
