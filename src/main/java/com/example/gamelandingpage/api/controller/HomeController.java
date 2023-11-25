package com.example.gamelandingpage.api.controller;

import com.example.gamelandingpage.repository.ImageRepository;
import com.example.gamelandingpage.repository.UserRepository;
import com.example.gamelandingpage.repository.model.Card;
import com.example.gamelandingpage.repository.model.PlatformUser;
import com.example.gamelandingpage.repository.model.data.Role;
import com.example.gamelandingpage.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Log4j2
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ImageRepository imageRepository;
    private final CardService cardService;
    private final UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model) {
        return "index.html";
    }

    @GetMapping("/profile/view")
    public String userProfile(Model model) {
        String platformId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        log.info("Getting profile for id: " + platformId);
        PlatformUser currentUser = userRepository.getPlatformUserByPlatformId(platformId).orElseThrow();

        model.addAttribute("user", currentUser);

        if (Role.ADMIN.equals(currentUser.getRole())) {
            model.addAttribute("isAdmin", true);
        }
        return "profile";
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

    @GetMapping(value = "/all-cards")
    public String cards(Model model) {
        return "allCards";
    }
}
