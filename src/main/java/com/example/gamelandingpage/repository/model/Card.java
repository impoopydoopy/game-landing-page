package com.example.gamelandingpage.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("cards")
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    private String id;
    private String imageId;
    private String cardName;
    private int cardHealth;
    private String cardDescription;
}
