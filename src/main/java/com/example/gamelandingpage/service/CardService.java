package com.example.gamelandingpage.service;

import com.example.gamelandingpage.api.model.CardCreateRequest;
import com.example.gamelandingpage.repository.CardRepository;
import com.example.gamelandingpage.repository.ImageRepository;
import com.example.gamelandingpage.repository.model.Card;
import com.example.gamelandingpage.repository.model.data.Image;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class CardService {

    private final ImageRepository imageRepository;
    private final CardRepository cardRepository;

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public void saveImage(MultipartFile file, String id) {
        Image img = new Image();
        try {
            Optional<Card> card = cardRepository.findById(id);
            if(card.isEmpty()) return;
            img.setName(file.getOriginalFilename());
            img.setContentType(file.getContentType());
            img.setImageContent(new Binary(file.getBytes()));
            Card card1 = card.get();
            card1.setImageId(imageRepository.save(img).getId());
            cardRepository.save(card1);
        } catch (IOException e) {
            log.error(e);
        }
    }

    public Image getImageById(String id) {
        return imageRepository.findById(id).orElseThrow();
    }

    public Card getCardById(String id) {
        log.info("ID: " + id);
        return cardRepository.findById(id).orElseThrow();
    }

    public Card createCard(CardCreateRequest request) {
        return cardRepository.save(Card.builder()
                .cardDescription(request.getCardDescription())
                .cardHealth(request.getCardHealth())
                .cardName(request.getCardName())
                .build());
    }
}
