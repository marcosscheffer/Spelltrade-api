package com.marcos.spelltrade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.marcos.spelltrade.domain.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
    
}
