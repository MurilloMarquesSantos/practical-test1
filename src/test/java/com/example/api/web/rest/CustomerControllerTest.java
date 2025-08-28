package com.example.api.web.rest;

import com.example.api.domain.Customer;
import com.example.api.domain.dto.CustomerDto;
import com.example.api.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;


    @Test
    public void testFindById() {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Murillo");

        when(customerService.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(customer));

        Customer response = customerController.findById(1L);

        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo("Murillo");
    }

    @Test
    public void testFindAll() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Murillo");

        Customer customer1 = new Customer();
        customer.setId(2L);
        customer.setName("Marques");

        PageImpl<Customer> customersPage = new PageImpl<>(Arrays.asList(customer, customer1));

        when(customerService.findAll(ArgumentMatchers.any()))
                .thenReturn(customersPage);

        ResponseEntity<Page<Customer>> page = customerController.findAll(null);

        assertThat(page).isNotNull();

        assertThat(page.getBody()).isNotNull();
    }

    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Murillo");

        when(customerService.save(ArgumentMatchers.any())).thenReturn(customer);

        ResponseEntity<Customer> savedCustomer = customerController.save(new CustomerDto("", ""));

        Customer customerBody = savedCustomer.getBody();

        assertThat(customerBody.getName()).isEqualTo("Murillo");

    }

    @Test
    public void testUpdate() {
        doNothing().when(customerService).update(ArgumentMatchers.any(), ArgumentMatchers.anyLong());

        assertThatCode(() -> customerController.update(new CustomerDto("", ""), 1L))
                .doesNotThrowAnyException();
    }

    @Test
    public void testDelete() {
        doNothing().when(customerService).delete(ArgumentMatchers.anyLong());

        assertThatCode(() -> customerController.delete(1L)).doesNotThrowAnyException();
    }
}