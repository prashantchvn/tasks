package com.example.practice.entities;

import com.example.practice.responseobjects.Address;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Users")
public class Users {
    @Id
    private String id;
    private String name;
    private Address address;
    private String password;
    private String email;
    private Boolean isAdmin;
    private Boolean isSeller;

    public Users(String id, String name, Address address, String password, String email, Boolean isAdmin, Boolean isSeller) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
        this.isSeller = isSeller;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getSeller() {
        return isSeller;
    }

    public void setSeller(Boolean seller) {
        isSeller = seller;
    }
}
