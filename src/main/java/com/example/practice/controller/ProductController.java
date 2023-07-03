package com.example.practice.controller;

import com.example.practice.entities.Product;
import com.example.practice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

class MongoRepositoryException extends RuntimeException {
    public MongoRepositoryException(String message) {
        super(message);
    }
}
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
   private ProductRepository productRepo;

    @GetMapping("/")
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") String id){
       return productRepo.findById(id).orElseThrow(() -> new MongoRepositoryException("Entity Not found"));
    }
    @PostMapping("/")
    public String createProduct(@RequestBody Product prod){
        productRepo.save(prod);
        return prod.getProductName() + " is added into our database";
    }
    @PutMapping("/{id}")
    public Product udpateProduct(@PathVariable("id") String id, @RequestBody Product pr){
        pr.setId(id);
        return productRepo.save(pr);
    }
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") String id){
        productRepo.deleteById(id);
        return "Product deleted successfully";
    }
}
