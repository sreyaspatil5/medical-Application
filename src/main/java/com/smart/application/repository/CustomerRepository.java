package com.smart.application.repository;

import com.smart.application.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Customer findByUsername(String username);

    Optional<Customer> findByName(String name);
}
