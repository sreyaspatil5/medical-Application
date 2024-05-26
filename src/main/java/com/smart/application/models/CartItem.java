package com.smart.application.models;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

@Entity
@Transactional
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String productName;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String customerName;

    public CartItem() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductName() {
        if (product != null) {
            return product.getName();
        }
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCustomerName() {
        if (customer != null) {
            return customer.getName();
        }
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Float getQuantity() {
        return this.product.getQuantity(); // Retrieve quantity from the Product entity
    }

}
