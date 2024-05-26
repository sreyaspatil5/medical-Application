package com.smart.application.services;

import com.smart.application.models.Customer;

import java.util.Optional;

public interface CustomerService {
    String signupCustomer(Customer customer);
    String signinCustomer(Customer customer);
    Optional<Customer> getCustomerById(Long id);
    Optional<Customer> getCustomerByName(String name);
    Customer save(Customer customer);
}

