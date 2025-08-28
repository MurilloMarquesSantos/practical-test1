package com.example.api.validator.impl;

import com.example.api.domain.dto.CustomerDto;
import com.example.api.exception.FieldValidationException;
import com.example.api.validator.ValidateData;
import org.springframework.stereotype.Component;

@Component
public class AddressesValidation implements ValidateData {

    @Override
    public void execute(CustomerDto customerDto) {
        customerDto.getAddresses().forEach(address -> {
            if (address.getStreet() == null || address.getStreet().isEmpty()) {
                throw new FieldValidationException("Street is required");
            }
            if (address.getCity() == null || address.getCity().isEmpty()) {
                throw new FieldValidationException("City is required");
            }
        });
    }
}
