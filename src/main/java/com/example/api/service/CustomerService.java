package com.example.api.service;

import com.example.api.domain.Customer;
import com.example.api.domain.dto.CustomerDto;
import com.example.api.exception.CustomerNotFoundException;
import com.example.api.repository.CustomerRepository;
import com.example.api.validator.ValidateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Page<Customer> findAll(Pageable pageable) {
        return repository.findAllByOrderByNameAsc(pageable);
    }

    public Optional<Customer> findById(Long id) {
        return repository.findById(id);
    }

    public Customer save(CustomerDto customerDto) {
        validateData(customerDto);
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setAddresses(customerDto.getAddresses());
        return repository.save(customer);
    }

    public void update(CustomerDto customerDto, Long id) {
        validateData(customerDto);
        Customer customer = findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        repository.save(customer);
    }

    private void validateData(CustomerDto customerDto) {
        for (ValidateData validation : strategyList) {
            validation.execute(customerDto);
        }
    }

    public void delete(Long id) {
        Customer customer = findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        repository.deleteById(customer.getId());
    }
}
