package com.smart.application.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.IOException;
import java.util.Base64;


@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] image;
    private String name;
    private String description;
    private String manufacturer;
    private Float price;
    private Float quantity;

    public String getImageBase64() throws IOException {
        if (image == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(image);
    }

    public void setImageBase64(String base64Image) {
        this.image = Base64.getDecoder().decode(base64Image);
    }

    public Product(Long id, byte[] image, String name, String description, String manufacturer, Float price, Float quantity) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
    }

    public Product() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    // Constructors, getters, and setters
    // ...

    // Additional fields and methods as needed
}

