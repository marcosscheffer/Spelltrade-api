package com.marcos.spelltrade.domain.entity;

import com.marcos.spelltrade.domain.enums.Color;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;

@Entity
@Table(
    schema = "card",
    name = "color_identity"
)
@Getter
@Setter
public class ColorIdentity {
    @EmbeddedId
    private ColorIdentityId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cardId")
    @JoinColumn(name = "card_id")
    private Card card;

    @Column(name = "color", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private Color color;
}
