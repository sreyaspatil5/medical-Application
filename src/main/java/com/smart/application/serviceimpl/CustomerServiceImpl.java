package com.smart.application.serviceimpl;

import com.smart.application.models.Customer;
import com.smart.application.repository.CustomerRepository;
import com.smart.application.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public String signupCustomer(Customer customer) {
        // Check if the username or email already exists
        if (customerRepository.existsByUsername(customer.getUsername())) {
            return "Username already exists";
        }
        if (customerRepository.existsByEmail(customer.getEmail())) {
            return "Email already exists";
        }

        // Save the customer
        customerRepository.save(customer);

        return "success";
    }

    @Override
    public Optional<Customer> getCustomerByName(String name) {
        return customerRepository.findByName(name);
    }

    public Customer merge(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public String signinCustomer(Customer customer) {
        // Find customer by username
        Customer existingCustomer = customerRepository.findByUsername(customer.getUsername());
        if (existingCustomer == null) {
            return "Customer not found";
        }

        return "success";
    }
}
