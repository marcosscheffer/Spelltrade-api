package com.marcos.spelltrade.domain.entity;

import java.util.UUID;
import com.marcos.spelltrade.domain.enums.SetType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    schema = "card",
    name = "sets")
@Getter
@Setter
public class Set {
    @Id
    private UUID id;
    private String name;
    private String code;

    @Enumerated(EnumType.STRING)
    private SetType type;
}
