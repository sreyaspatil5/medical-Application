package com.smart.application.controller;

import com.smart.application.models.Product;
import com.smart.application.pojo.SearchCriteria;
import com.smart.application.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<Object> getAllProducts(@RequestParam(required = false) boolean includeImage) throws IOException {
        List<Product> products = productRepository.findAll();
        if (includeImage) {
            for (Product product : products) {
                String imageBase64 = product.getImageBase64();
                product.setImageBase64(imageBase64);
            }
        }
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Object> getProductById(@PathVariable Long productId,
                                                 @RequestParam(required = false) boolean includeImage) throws IOException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (includeImage) {
            String imageBase64 = product.getImageBase64();
            product.setImageBase64(imageBase64);
        }
        return ResponseEntity.ok().body(product);
    }

    @PostMapping("/search")
    public List<Product> searchProductsByName(@RequestBody SearchCriteria searchCriteria) {
        String name = searchCriteria.getName();
        // Use the name to search for products
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @PostMapping
    public Product addProduct(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") Float price,
            @RequestParam("quantity") Float quantity,
            @RequestParam("manufacturer") String manufacturer, // New parameter
            @RequestParam("image") MultipartFile imageFile) throws Exception {

        // Create a new Product instance and set its properties
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setManufacturer(manufacturer); // Set manufacturer
        product.setImage(imageFile.getBytes()); // Store image data

        // Validate and save the product
        return productRepository.save(product);
    }

    @PutMapping("/updatebyname")
    public Product updateProductByName(
            @RequestParam String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Float price,
            @RequestParam(required = false) Float quantity,
            @RequestParam(required = false) String manufacturer,
            @RequestParam(required = false) MultipartFile imageFile) throws Exception {

        // Search for the product by name
        List<Product> productsWithName = productRepository.findByNameContainingIgnoreCase(name);
        if (productsWithName.isEmpty()) {
            throw new RuntimeException("Product not found with name: " + name);
        }

        // Assuming there's only one product with the given name (you can handle multiple matches if needed)
        Product existingProduct = productsWithName.get(0);

        // Update fields (if provided)
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

        // Save the updated product
        return productRepository.save(existingProduct);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(
            @PathVariable Long productId,
            @RequestBody Product updatedProduct) {

        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Update fields (if provided)
        if (updatedProduct.getName() != null) {
            existingProduct.setName(updatedProduct.getName());
        }
        if (updatedProduct.getDescription() != null) {
            existingProduct.setDescription(updatedProduct.getDescription());
        }
        if (updatedProduct.getPrice() != null) {
            existingProduct.setPrice(updatedProduct.getPrice());
        }
        if (updatedProduct.getQuantity() != -1) {
            existingProduct.setQuantity(updatedProduct.getQuantity());
        }
        if (updatedProduct.getManufacturer() != null) {
            existingProduct.setManufacturer(updatedProduct.getManufacturer());
        }
        if (updatedProduct.getImage() != null) {
            existingProduct.setImage(updatedProduct.getImage());
        }

        // Save the updated product
        return productRepository.save(existingProduct);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productRepository.deleteById(productId);
    }
  // Other endpoints as needed
}
