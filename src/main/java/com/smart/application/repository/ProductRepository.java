package com.smart.application.repository;

import com.smart.application.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(String name);

    Optional<Product> findByName(String name);

    // Other custom queries if needed
}


