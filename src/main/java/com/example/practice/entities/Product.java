package com.example.practice.entities;

import com.example.practice.responseobjects.UserResObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("product")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private Integer price;
    private String company;
    private UserResObject seller;

    public Product(String id, String name, String description, Integer price, String company, UserResObject seller) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.company = company;
        this.seller = seller;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public UserResObject getSeller() {
        return seller;
    }

    public void setSeller(UserResObject seller) {
        this.seller = seller;
    }
}
