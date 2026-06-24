CREATE TABLE inventory.storages_cards (
    card_id UUID NOT NULL,
    storage_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    available BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    active BOOLEAN NOT NULL DEFAULT TRUE,

    PRIMARY KEY (card_id, storage_id),

    CONSTRAINT fk_storages_cards_cards
        FOREIGN KEY (card_id)
        REFERENCES card.cards(id),

    CONSTRAINT fk_storages_cards_storages
        FOREIGN KEY (storage_id)
        REFERENCES inventory.storages(id)
)