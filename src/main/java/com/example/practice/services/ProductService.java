package com.example.practice.services;

import com.example.practice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;
    public ResponseEntity<String> testProductApi(){
        return ResponseEntity.ok("Product api is working");
    }
}
