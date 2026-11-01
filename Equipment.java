package com.farmequipment.model;

import jakarta.persistence.*;

@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String category;
    private String brand;
    private String model;
    private String description;

    @Column(name = "price_per_day")
    private double pricePerDay;

    private String location;
    private String image;
    private String availability;
    @Column(name = "owner_id")
    private Integer ownerId;
    // Default constructor
    public Equipment() {
    }

    // Parameterized constructor
    public Equipment(String name, String category, String brand,
                     String model, String description, double pricePerDay,
                     String location, String image, String availability) {

        this.name = name;
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.location = location;
        this.image = image;
        this.availability = availability;
    }

    // Getters and Setters
    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
