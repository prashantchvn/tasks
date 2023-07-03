package com.example.practice.controller;

import com.example.practice.entities.Users;
import com.example.practice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/")
    public ResponseEntity<String> testingApi(){
        return userService.testApi();
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Users user){
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Users user){
        return userService.loginUser(user);
    }
}
