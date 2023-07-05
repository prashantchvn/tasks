package com.example.practice.controller;

import com.example.practice.entities.Users;
import com.example.practice.interceptors.AuthTokenInterceptor;
import com.example.practice.responseobjects.ValidateUserResObject;
import com.example.practice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    private final AuthTokenInterceptor authTokenInterceptor;

    public UserController(AuthTokenInterceptor authTokenInterceptor) {
        this.authTokenInterceptor = authTokenInterceptor;
    }

    @GetMapping("/test")
    public ResponseEntity<String> testingApi(){
        return userService.testApi();
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Users user){
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody Users user){
        return userService.loginUser(user);
    }
    @GetMapping("/")
    public ResponseEntity<Object> getUserFromToken(@RequestHeader("Authorization") String authToken){
        ValidateUserResObject matchedUser = userService.getUserFromToken(authToken.substring(7));
        if (matchedUser == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\":\"Invalid Auth token\"}");
        }else{
            return ResponseEntity.ok().body(matchedUser);
        }
    }

    @GetMapping("/admin")
    public String checkAuthentication(){
        return userService.checkAuthorizedUser();
    }
}
