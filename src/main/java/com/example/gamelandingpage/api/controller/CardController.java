package com.example.gamelandingpage.api.controller;

import com.example.gamelandingpage.api.model.CardCreateRequest;
import com.example.gamelandingpage.repository.model.Card;
import com.example.gamelandingpage.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping()
    public ResponseEntity<Card> createCard(@RequestBody CardCreateRequest request) {
        return ResponseEntity.ok(cardService.createCard(request));
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        return ResponseEntity.ok(cardService.getAllCards());
    }
}
