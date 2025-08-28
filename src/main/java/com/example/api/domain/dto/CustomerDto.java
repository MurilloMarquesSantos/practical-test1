package com.example.api.domain.dto;

import com.example.api.domain.Address;

import java.util.List;

public class CustomerDto {

    private String name;

    private String email;

    private List<Address> addresses;

    public List<Address> getAddresses() {
        return addresses;
    }

    public CustomerDto setAddresses(List<Address> addresses) {
        this.addresses = addresses;
        return this;
    }

    public String getName() {
        return name;
    }

    public CustomerDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomerDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
