package com.example.practice.controller;

import com.example.practice.entities.Product;
import com.example.practice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/test")
    public ResponseEntity<String> testProductApi(){
        return productService.testProductApi();
    }

    @PostMapping("/create")
    public ResponseEntity<Object> SaveProduct(@RequestBody Product product, @RequestHeader("Authorization") String authToken){
        return productService.createProduct(product,authToken.substring(7));
    }

    @GetMapping("/")
    public ResponseEntity<Object> showProducts(){
        return productService.viewAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSingleProduct(@PathVariable("id") String id){
        return productService.getSingleProduct(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id,@RequestBody Product product, @RequestHeader("Authorization") String authToken){
        return productService.updateSingleProduct(product,id,authToken.substring(7));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") String id,@RequestHeader("Authorization") String authToken){
        return productService.deleteSingleProduct(id,authToken.substring(7));
    }
}
