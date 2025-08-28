package com.example.api.web.rest;

import com.example.api.domain.Customer;
import com.example.api.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

        when(customerService.findById(1L)).thenReturn(Optional.of(customer));

        Customer response = customerController.findById(1L);

        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo("Murillo");
    }

    public void testFindAll() {
    }

    public void testSave() {
    }

    public void testUpdate() {
    }

    public void testDelete() {
    }
}