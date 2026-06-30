package com.marcos.spelltrade.domain.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.io.Serializable;
import java.util.UUID;
import com.marcos.spelltrade.domain.enums.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ColorIdentityId implements Serializable {
    private UUID cardId;
    
    @Enumerated(EnumType.STRING)
    private Color color;
}
