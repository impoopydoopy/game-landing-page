package com.example.gamelandingpage.service;

import com.example.gamelandingpage.repository.UserRepository;
import com.example.gamelandingpage.repository.model.PlatformUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagementService {

    private final UserRepository userRepository;

    public boolean userPresentByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void createUser(PlatformUser user) {
        userRepository.save(user);
    }
}
