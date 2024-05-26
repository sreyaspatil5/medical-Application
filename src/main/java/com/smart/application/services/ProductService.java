package com.smart.application.services;


import com.smart.application.models.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    List<Product> searchProductsByName(String name);
    Product addProduct(String name, String description, Float price, Float quantity, String manufacturer, MultipartFile imageFile) throws IOException;

    Product updateProductByName(String name, String description, Float price, Float quantity, String manufacturer, MultipartFile imageFile) throws IOException;

    Product updateProduct(Long productId, Product updatedProduct);
    
    void deleteProduct(Long productId);

    Optional<Product> getProductById(Long id);
    Optional<Product> getProductByName(String name);
    Product save(Product product);
}

