package com.example.api.service;

import com.example.api.domain.Customer;
import com.example.api.domain.dto.CustomerDto;
import com.example.api.repository.CustomerRepository;
import com.example.api.validator.ValidateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository repository;

    private List<ValidateData> strategyList;

    @Autowired
    public CustomerService(CustomerRepository repository, List<ValidateData> strategies) {
        this.repository = repository;
        this.strategyList = strategies;
    }

    public List<Customer> findAll() {
        return repository.findAllByOrderByNameAsc();
    }

    public Optional<Customer> findById(Long id) {
        return repository.findById(id);
    }

    public Customer save(CustomerDto customerDto) {
        validateData(customerDto);
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        return repository.save(customer);
    }

    private void validateData(CustomerDto customerDto) {
        for (ValidateData validation : strategyList) {
            validation.execute(customerDto);
        }
    }
}
