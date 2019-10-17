package io.javabrains.springsecurityjpa;

import io.javabrains.springsecurityjpa.models.User;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
   }
