package com.example.practice.repository;

import com.example.practice.entities.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<Users, String> {
    Users findByEmail(String email);
}
