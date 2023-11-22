package com.example.gamelandingpage.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("users")
@AllArgsConstructor
@NoArgsConstructor
public class PlatformUser {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
}
