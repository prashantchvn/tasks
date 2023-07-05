package com.example.practice.responseobjects;

public class UserResObject {

    private String ID;
    private String name;
    private String email;
    private Address address;

    public UserResObject(String ID, String name, String email, Address address) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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
}
