package com.example.practice.responseobjects;

public class ValidateUserResObject {
    private String id;
    private String name;
    private String email;
    private Address address;
    private Boolean isAdmin;
    private Boolean isSeller;

    public ValidateUserResObject(String id, String name, String email, Address address, Boolean isAdmin, Boolean isSeller) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
