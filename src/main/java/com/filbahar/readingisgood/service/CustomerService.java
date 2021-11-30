package com.filbahar.readingisgood.service;

import com.filbahar.readingisgood.model.Customer;

/**
 * @author filbahar
 * @created 29.11.2021
 */
public interface CustomerService {

    Customer saveCustomer(Customer customer);

    Customer findCustomerById(String id);

    Customer findCustomerByEmail(String email);

}
