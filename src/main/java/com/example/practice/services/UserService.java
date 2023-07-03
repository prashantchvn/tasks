package com.example.practice.services;

import com.example.practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    public ResponseEntity<String> testApi(){
        return ResponseEntity.ok("Okay");
    }
}
