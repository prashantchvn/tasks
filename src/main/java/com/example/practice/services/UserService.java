package com.example.practice.services;

import com.example.practice.entities.Users;
import com.example.practice.repository.UserRepository;
import com.example.practice.responseobjects.ValidateUserResObject;
import com.example.practice.utilities.JwtUtils;
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
    private JwtUtils jwtUtils;

    public UserService(PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
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

    public ResponseEntity<Object> loginUser(Users user){
        Users matchedUser = userRepo.findByEmail(user.getEmail());
        if(matchedUser == null){
            return ResponseEntity.notFound().build();
        }
        if(passwordEncoder.matches(user.getPassword(),matchedUser.getPassword())){
            String token = jwtUtils.generateToken(user.getEmail());
            return ResponseEntity.ok().body("{\"token:\" \"" + "Bearer " + token + "\" }");
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message:\": \"Invalid Credentials\"}");
        }
    }

    public ValidateUserResObject getUserFromToken(String token){
        String email = jwtUtils.getUsernameFromToken(token);
        Users matchedUser = userRepo.findByEmail(email);
        ValidateUserResObject userResObject = new ValidateUserResObject(matchedUser.getId(),matchedUser.getName(),matchedUser.getEmail(),matchedUser.getAddress(),matchedUser.getAdmin(),matchedUser.getSeller());
        return userResObject;
    }

    public String checkAuthorizedUser(){
        return "Authorized User";
    }

}
