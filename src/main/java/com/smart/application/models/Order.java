package com.smart.application.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "customer_name", referencedColumnName = "username")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_name", referencedColumnName = "name")
    private Product product;

    private Float quantity;

    private Date orderDate;

    // Additional fields derived from associated entities
    @Transient
    private Long customerId;

    @Transient
    private String customerName;

    @Transient
    private String customerAddress;

    @Transient
    private Float price;

    @Transient
    private String productName;


    // Constructors, getters, and setters
    // ...

    public Long getCustomerId() {
        return customer != null ? customer.getId() : null;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customer != null ? customer.getUsername() : null;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customer != null ? customer.getAddress() : null;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Float getPrice() {
        return product != null ? product.getPrice() : null;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getProductName() {
        return product != null ? product.getName() : null;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
