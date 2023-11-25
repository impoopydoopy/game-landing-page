package com.example.gamelandingpage.repository.model.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("images")
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    private String id;
    private String name;
    private String contentType;
    private Binary imageContent; // Stores the actual image data
}
