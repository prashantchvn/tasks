package com.example.practice.services;

import com.example.practice.entities.Product;
import com.example.practice.entities.Users;
import com.example.practice.repository.ProductRepository;
import com.example.practice.repository.UserRepository;
import com.example.practice.responseobjects.UserResObject;
import com.example.practice.utilities.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public ResponseEntity<Object> getSingleProduct(String id){
        Product product = productRepo.findById(id).get();
        return ResponseEntity.ok(product);
    }

    public ResponseEntity<Object> updateSingleProduct(Product product, String id, String token){
        String email = jwtUtils.getUsernameFromToken(token);
        Users matchedUser = userRepository.findByEmail(email);
        UserResObject userResObject = new UserResObject(matchedUser.getId(),matchedUser.getName(),matchedUser.getEmail(),matchedUser.getAddress());
        UserResObject seller = productRepo.findById(id).get().getSeller();
        if(matchedUser.getId().equals(seller.getID()) || matchedUser.getAdmin()){
            product.setId(id);
            product.setSeller(userResObject);
            productRepo.save(product);
            return ResponseEntity.ok(product);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\":\"You don't have permission to edit this product\"}");
        }
    }

    public ResponseEntity<Object> deleteSingleProduct(String id, String token){
        String email = jwtUtils.getUsernameFromToken(token);
        Users matchedUser = userRepository.findByEmail(email);
        Product product = productRepo.findById(id).get();
        UserResObject seller = product.getSeller();
        if(matchedUser.getId().equals(seller.getID()) || matchedUser.getAdmin()){
            productRepo.deleteById(id);
            return ResponseEntity.ok().body("{\"message\":\"Product deleted successfully\"}");
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\":\"You don't have permission to edit this product\"}");
        }
    }
}
