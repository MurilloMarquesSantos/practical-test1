package com.example.api.validator;

import com.example.api.domain.dto.CustomerDto;

public interface ValidateData {

    void execute(CustomerDto customerDto);
}
