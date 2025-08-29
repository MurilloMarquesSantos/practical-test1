package com.example.api.web.rest;

import com.example.api.domain.Customer;
import com.example.api.domain.dto.CustomerDto;
import com.example.api.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/customers")
@Tag(name = "Customer", description = "Customer endpoints")
public class CustomerController {

    private CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }


    @GetMapping("/{id}")
    @Operation(summary = "Find By Id", description = "Retorna Customer pelo Id", tags = {"Customer"})
    @ApiResponse(responseCode = "200", description = "Customer encontrado")
    @ApiResponse(responseCode = "404", description = "Customer não encontrado",
    content = @Content(examples = @ExampleObject(value = "Customer não encontrado")))
    public Customer findById(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    @GetMapping
    @Operation(summary = "Find All", description = "Retorna todos os Customers", tags = {"Customer"})
    @ApiResponse(responseCode = "200", description = "Customers encontrados")
    @ApiResponse(responseCode = "400", description = "Erro ao retornar customers",
            content = @Content(examples = @ExampleObject(value = "Erro ao retornar customers")))
    public ResponseEntity<Page<Customer>> findAll(Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Save", description = "Retorna Customer criado", tags = {"Customer"})
    @ApiResponse(responseCode = "201", description = "Customer criado")
    @ApiResponse(responseCode = "400", description = "Customer tem campos invalidos",
            content = @Content(examples = @ExampleObject(value = "Customer tem campos invalidos")))
    public ResponseEntity<Customer> save(@RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(service.save(customerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "Atualiza Customer", tags = {"Customer"})
    @ApiResponse(responseCode = "204", description = "Customer Atualizado")
    @ApiResponse(responseCode = "400", description = "Customer tem campos invalidos",
            content = @Content(examples = @ExampleObject(value = "Customer tem campos invalidos")))
    public ResponseEntity<Void> update(@RequestBody CustomerDto customerDto, @PathVariable Long id) {
        service.update(customerDto, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete", description = "Excluí Customer", tags = {"Customer"})
    @ApiResponse(responseCode = "204", description = "Customer Excluído")
    @ApiResponse(responseCode = "400", description = "Customer não existe por Id",
            content = @Content(examples = @ExampleObject(value = "Customer não existe por Id")))
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
