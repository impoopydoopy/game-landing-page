package com.example.gamelandingpage.repository;

import com.example.gamelandingpage.repository.model.PlatformUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<PlatformUser, String> {

    boolean existsByEmail(String email);
}
