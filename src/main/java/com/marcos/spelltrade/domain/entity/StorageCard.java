package com.marcos.spelltrade.domain.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    schema = "inventory",
    name = "storages_cards"
)
@Getter
@Setter
public class StorageCard {
    @EmbeddedId
    private StorageCardId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cardId")
    @JoinColumn(name = "card_id")
    private Card card;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("storageId")
    @JoinColumn(name = "storage_id")
    private Storage storage;
    private int quantity;
    private Boolean available;
}
