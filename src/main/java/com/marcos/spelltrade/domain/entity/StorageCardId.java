package com.marcos.spelltrade.domain.entity;

import java.io.Serializable;
import java.util.UUID;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class StorageCardId implements Serializable {
    private UUID cardId;
    private Long storageId;
}
