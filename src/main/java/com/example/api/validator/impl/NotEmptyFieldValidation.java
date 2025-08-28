package com.example.api.validator.impl;

import com.example.api.domain.dto.CustomerDto;
import com.example.api.exception.FieldValidationException;
import com.example.api.validator.ValidateData;
import org.springframework.stereotype.Component;

@Component
public class NotEmptyFieldValidation implements ValidateData {

    @Override
    public void execute(CustomerDto customerDto) {
        if (customerDto.getEmail().isEmpty() || customerDto.getEmail() == null) {
            throw new FieldValidationException("Email is required");
        }
        if (customerDto.getName().isEmpty() || customerDto.getName() == null) {
            throw new FieldValidationException("Name is required");
        }
    }
}
