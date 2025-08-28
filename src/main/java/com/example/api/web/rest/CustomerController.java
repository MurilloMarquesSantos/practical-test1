package com.example.api.web.rest;

import com.example.api.domain.Customer;
import com.example.api.domain.dto.CustomerDto;
import com.example.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Customer> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(service.save(customerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody CustomerDto customerDto, @PathVariable Long id) {
        service.update(customerDto, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
