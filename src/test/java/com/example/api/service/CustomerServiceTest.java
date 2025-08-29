package com.example.api.service;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.domain.dto.CustomerDto;
import com.example.api.repository.CustomerRepository;
import com.example.api.validator.ValidateData;
import com.example.api.validator.impl.AddressesValidation;
import com.example.api.validator.impl.EmailValidation;
import com.example.api.validator.impl.NotEmptyFieldValidation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AddressesValidation addressesValidation;

    @Mock
    private EmailValidation emailValidation;

    @Mock
    private NotEmptyFieldValidation notEmptyFieldValidation;


    @Test
    public void testFindAll() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Murillo");

        Customer customer1 = new Customer();
        customer.setId(2L);
        customer.setName("Marques");

        customerRepository.save(customer);
        customerRepository.save(customer1);

        PageImpl<Customer> customersPage = new PageImpl<>(Arrays.asList(customer, customer1));

        when(customerRepository.findAllByOrderByNameAsc(ArgumentMatchers.any())).thenReturn(customersPage);

        Page<Customer> customers = customerService.findAll(null);

        assertThat(customers.getContent()).hasSize(2);
    }

    @Test
    public void testFindById() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Murillo");

        customerRepository.save(customer);

        when(customerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(customer));

        Optional<Customer> customerOpt = customerService.findById(1L);

        assertThat(customerOpt).isPresent();

        assertThat(customerOpt.get().getName()).isEqualTo("Murillo");
    }

    @Test
    public void testSave() {

        List<ValidateData> validateDataList =
                Arrays.asList(addressesValidation, emailValidation, notEmptyFieldValidation);

        ReflectionTestUtils.setField(customerService, "strategyList", validateDataList);

        doNothing().when(addressesValidation).execute(ArgumentMatchers.any());
        doNothing().when(emailValidation).execute(ArgumentMatchers.any());
        doNothing().when(notEmptyFieldValidation).execute(ArgumentMatchers.any());

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Murillo");
        customer.setEmail("murillomarques@gmail.com");

        when(customerRepository.save(ArgumentMatchers.any(Customer.class))).thenReturn(customer);

        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Murillo");
        customerDto.setEmail("murillomarques@gmail.com");
        customerDto.setAddresses(Arrays.asList(new Address(), new Address()));

        Customer savedCustomer = customerService.save(customerDto);

        assertThat(savedCustomer).isNotNull();

        assertThat(savedCustomer.getEmail()).isEqualTo("murillomarques@gmail.com");
    }

    @Test
    public void testUpdate() {

        List<ValidateData> validateDataList =
                Arrays.asList(addressesValidation, emailValidation, notEmptyFieldValidation);

        ReflectionTestUtils.setField(customerService, "strategyList", validateDataList);

        doNothing().when(addressesValidation).execute(ArgumentMatchers.any());
        doNothing().when(emailValidation).execute(ArgumentMatchers.any());
        doNothing().when(notEmptyFieldValidation).execute(ArgumentMatchers.any());

        Address address = new Address();
        address.setId(2L);
        address.setStreet("Rua 1");

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Murillo");
        customer.setEmail("murillomarques@gmail.com");
        customer.setAddresses(new ArrayList<>(Arrays.asList(address)));

        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Murillo");
        customerDto.setEmail("murillomarques@gmail.com");
        customerDto.setAddresses(Arrays.asList(new Address(), new Address()));

        when(customerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(customer));

        assertThatCode(() -> customerService.update(customerDto, 1L)).doesNotThrowAnyException();

    }

    @Test
    public void testDelete() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Murillo");
        customer.setEmail("murillomarques@gmail.com");

        when(customerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(customer));
        doNothing().when(customerRepository).deleteById(ArgumentMatchers.anyLong());

        assertThatCode(() -> customerService.delete(1L)).doesNotThrowAnyException();
    }


}