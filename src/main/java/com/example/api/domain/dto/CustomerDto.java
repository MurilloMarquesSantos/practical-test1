package com.example.api.domain.dto;

public class CustomerDto {

    private String name;

    private String email;

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
