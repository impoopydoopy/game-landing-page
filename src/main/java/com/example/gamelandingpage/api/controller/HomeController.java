package com.example.gamelandingpage.api.controller;

import com.example.gamelandingpage.repository.ImageRepository;
import com.example.gamelandingpage.repository.model.Card;
import com.example.gamelandingpage.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.example.gamelandingpage.service.utils.UrlBuilder.SUCCESS_URI_PREFIX;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ImageRepository imageRepository;
    private final CardService cardService;

    @GetMapping("/")
    public String home(Model model) {
        return "index.html";
    }

    @GetMapping(value = "/img")
    public String image(Model model) {
        return "photo";
    }

    @GetMapping(value = "/create-card")
    public String cardCreator(Model model) {
        return "cardCreate";
    }

    @GetMapping(value = "/get-card/{id}")
    public String cardCreator(@PathVariable("id") String id, Model model) {
        Card card = cardService.getCardById(id);

        model.addAttribute("cardId", card.getId());
        model.addAttribute("cardName", card.getCardName());
        model.addAttribute("cardHealth", card.getCardHealth());
        model.addAttribute("cardDescription", card.getCardDescription());

        if (card.getImageId() != null && !card.getImageId().isEmpty()) {
            model.addAttribute("imageId", card.getImageId());
        }

        return "cardView";
    }
}
