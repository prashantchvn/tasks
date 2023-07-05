package com.example.practice.services;

import com.example.practice.entities.Product;
import com.example.practice.entities.Users;
import com.example.practice.repository.ProductRepository;
import com.example.practice.repository.UserRepository;
import com.example.practice.responseobjects.UserResObject;
import com.example.practice.utilities.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private UserRepository userRepository;
    private final JwtUtils jwtUtils;
    public ProductService(JwtUtils jwtUtils){
        this.jwtUtils = jwtUtils;
    }
    public ResponseEntity<String> testProductApi(){
        return ResponseEntity.ok("Product api is working");
    }
    public ResponseEntity<Object> createProduct(Product product, String token){
        String email = jwtUtils.getUsernameFromToken(token);
        Users seller = userRepository.findByEmail(email);
        UserResObject userResObject = new UserResObject(seller.getId(),seller.getName(),seller.getEmail(),seller.getAddress());
        product.setSeller(userResObject);
        productRepo.save(product);
        return ResponseEntity.ok(product);
    }
    public ResponseEntity<Object> viewAllProducts(){
        List<Product> productList = productRepo.findAll();
        return ResponseEntity.ok(productList);
    }
}
