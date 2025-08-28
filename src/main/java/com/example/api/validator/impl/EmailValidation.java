package com.example.api.validator.impl;

import com.example.api.domain.dto.CustomerDto;
import com.example.api.exception.FieldValidationException;
import com.example.api.validator.ValidateData;
import org.springframework.stereotype.Component;

@Component
public class EmailValidation implements ValidateData {

    private static final String REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    @Override
    public void execute(CustomerDto customerDto) {
        if(!customerDto.getEmail().matches(REGEX)) {
            throw new FieldValidationException("Invalid email!");
        }
    }

}
