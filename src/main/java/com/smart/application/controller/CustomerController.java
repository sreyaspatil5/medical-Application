package com.smart.application.controller;

import com.smart.application.models.Customer;
import com.smart.application.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signupCustomer(@RequestBody Customer customer) {
        String result = customerService.signupCustomer(customer);
        if (result != null && result.equals("success")) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Customer signed up successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to sign up customer");
        }
    }


    @PostMapping("/signin")
    public ResponseEntity<String> signinCustomer(@RequestBody Customer customer) {
        String result = customerService.signinCustomer(customer);
        if (result.equals("success")) {
            return ResponseEntity.status(HttpStatus.OK).body("Customer signed in successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
