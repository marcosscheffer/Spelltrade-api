package com.marcos.spelltrade.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    schema = "card",
    name = "images"
)
@Getter
@Setter
public class CardImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "card_id")
    private Card card;
    private Long cardFace;
    private Short faceIndex;
    private String small;
    private String normal;
    private String large;
    private String png;
    private String borderCrop;
    private String artCrop;
}
