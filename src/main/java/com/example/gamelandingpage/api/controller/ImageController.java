package com.example.gamelandingpage.api.controller;

import com.example.gamelandingpage.repository.ImageRepository;
import com.example.gamelandingpage.repository.model.data.Image;
import com.example.gamelandingpage.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageRepository imageRepository;
    private final CardService cardService;

    @PostMapping("/upload/{id}")
    public ResponseEntity<Void> uploadImage(@PathVariable String id, @RequestParam("image") MultipartFile file) {
        cardService.saveImage(file, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Image>> getAll() {
        return ResponseEntity.ok(imageRepository.findAll());
    }

    @GetMapping(value = "/download/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable String id) {
        Image image = cardService.getImageById(id);
        byte[] imageContent = image.getImageContent().getData();
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(image.getContentType()))
                .body(imageContent);
    }
}
