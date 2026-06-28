package com.marcos.spelltrade.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.marcos.spelltrade.domain.entity.Storage;
import com.marcos.spelltrade.domain.enums.Status;

public interface StorageRepository extends JpaRepository<Storage, Long> {
    Page<Storage> findByStatusAndNameContainingIgnoreCase(
        Status status, String name, Pageable pageable);
}
