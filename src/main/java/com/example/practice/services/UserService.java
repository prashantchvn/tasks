package com.example.practice.services;

import com.example.practice.entities.Users;
import com.example.practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    public ResponseEntity<String> testApi(){
        return ResponseEntity.ok("Okay");
    }

    public ResponseEntity<String> registerUser(Users user){
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepo.save(user);
        return ResponseEntity.ok("User registered please login in using authentication");
    }

    public ResponseEntity<String> loginUser(Users user){
        Users matchedUser = userRepo.findByEmail(user.getEmail());
        if(matchedUser == null){
            return ResponseEntity.notFound().build();
        }
        if(passwordEncoder.matches(user.getPassword(),matchedUser.getPassword())){
            return ResponseEntity.ok("Login successfull");
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }


}
