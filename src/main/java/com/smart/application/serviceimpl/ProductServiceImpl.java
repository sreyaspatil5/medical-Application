package com.smart.application.serviceimpl;


import com.smart.application.models.Product;
import com.smart.application.repository.ProductRepository;
import com.smart.application.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(Long productId) {
        return Optional.ofNullable(productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found")));
    }



    @Override
    public Optional<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }


    @Override
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Product addProduct(String name, String description, Float price, Float quantity, String manufacturer, MultipartFile imageFile) throws IOException {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setManufacturer(manufacturer);
        product.setImage(imageFile.getBytes());
        return productRepository.save(product);
    }

    @Override
    public Product updateProductByName(String name, String description, Float price, Float quantity, String manufacturer, MultipartFile imageFile) throws IOException {
        List<Product> productsWithName = productRepository.findByNameContainingIgnoreCase(name);
        if (productsWithName.isEmpty()) {
            throw new RuntimeException("Product not found with name: " + name);
        }
        Product existingProduct = productsWithName.get(0);
        if (description != null) {
            existingProduct.setDescription(description);
        }
        if (price != null) {
            existingProduct.setPrice(price);
        }
        if (quantity != null) {
            existingProduct.setQuantity(quantity);
        }
        if (manufacturer != null) {
            existingProduct.setManufacturer(manufacturer);
        }
        if (imageFile != null) {
            existingProduct.setImage(imageFile.getBytes());
        }
        return productRepository.save(existingProduct);
    }

    @Override
    public Product updateProduct(Long productId, Product updatedProduct) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setQuantity(updatedProduct.getQuantity());
        existingProduct.setManufacturer(updatedProduct.getManufacturer());
        existingProduct.setImage(updatedProduct.getImage());
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}

