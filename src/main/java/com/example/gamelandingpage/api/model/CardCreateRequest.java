package com.example.gamelandingpage.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardCreateRequest {

    private String cardName;
    private int cardHealth;
    private String cardDescription;
}
