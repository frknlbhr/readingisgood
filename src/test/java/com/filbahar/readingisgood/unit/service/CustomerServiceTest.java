package com.filbahar.readingisgood.unit.service;

import com.filbahar.readingisgood.exception.EntityNotFoundException;
import com.filbahar.readingisgood.model.Customer;
import com.filbahar.readingisgood.repo.CustomerRepository;
import com.filbahar.readingisgood.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

/**
 * @author filbahar
 * @created 29.11.2021
 */

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customerFromDb;

    @BeforeEach
    public void setup() {
        customerFromDb = new Customer();
        customerFromDb.setId("customerId");
        customerFromDb.setFirstName("Ali");
        customerFromDb.setLastName("Veli");
        customerFromDb.setEmail("aliveli@gmail.com");
    }

    @Test
    public void test_saveCustomer() {
        Mockito.when(customerRepository.save(Mockito.isA(Customer.class))).thenReturn(customerFromDb);

        Customer result = customerService.saveCustomer(new Customer());
        Assertions.assertEquals(customerFromDb.getId(), result.getId());
        Assertions.assertEquals(customerFromDb.getFirstName(), result.getFirstName());
        Assertions.assertEquals(customerFromDb.getLastName(), result.getLastName());
        Assertions.assertEquals(customerFromDb.getEmail(), result.getEmail());
        Mockito.verify(customerRepository, Mockito.times(1)).save(Mockito.isA(Customer.class));
    }

    @Test
    public void test_findCustomerById_success() {
        Mockito.when(customerRepository.findById(Mockito.anyString())).thenReturn(Optional.of(customerFromDb));

        Customer result = customerService.findCustomerById("id");
        Assertions.assertEquals(customerFromDb.getId(), result.getId());
        Assertions.assertEquals(customerFromDb.getFirstName(), result.getFirstName());
        Assertions.assertEquals(customerFromDb.getLastName(), result.getLastName());
        Assertions.assertEquals(customerFromDb.getEmail(), result.getEmail());
        Mockito.verify(customerRepository, Mockito.times(1)).findById(Mockito.anyString());
    }

    @Test
    public void test_findCustomerById_fail() {
        Mockito.when(customerRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> customerService.findCustomerById("id"));
    }

    @Test
    public void test_findCustomerByEmail() {
        Mockito.when(customerRepository.findByEmail(Mockito.anyString())).thenReturn(customerFromDb);

        Customer result = customerService.findCustomerByEmail("email@email.com");
        Assertions.assertEquals(customerFromDb.getId(), result.getId());
        Assertions.assertEquals(customerFromDb.getEmail(), result.getEmail());
        Mockito.verify(customerRepository, Mockito.times(1)).findByEmail(Mockito.anyString());
    }
}
