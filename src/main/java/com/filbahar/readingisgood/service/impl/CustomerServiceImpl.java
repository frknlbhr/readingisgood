package com.filbahar.readingisgood.service.impl;

import com.filbahar.readingisgood.exception.EntityNotFoundException;
import com.filbahar.readingisgood.model.Customer;
import com.filbahar.readingisgood.repo.CustomerRepository;
import com.filbahar.readingisgood.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author filbahar
 * @created 29.11.2021
 */

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerById(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer with id = " + id + " not found"));
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}
