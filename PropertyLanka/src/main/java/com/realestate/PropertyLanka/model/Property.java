package com.realestate.PropertyLanka.model;

public class Property {
    private String id;
    private String title;
    private String description;
    private double price;
    private String address;
    private String propertyType;
    private String status;
    private String image; // Just a text link or file name for now (e.g., "house1.png")
    private String createdDate;

    public Property(String id, String title, String description, double price, String address, String propertyType, String status, String image, String createdDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.address = address;
        this.propertyType = propertyType;
        this.status = status;
        this.image = image;
        this.createdDate = createdDate;
    }

    // Turns the object into a single line for the text file
    public String toDatabaseString() {
        return id + "|" + title + "|" + description + "|" + price + "|" +
                address + "|" + propertyType + "|" + status + "|" + image + "|" + createdDate;
    }

    // Getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public double getPrice() { return price; }
    public String getStatus() { return status; }

    // Setters (Needed for the UPDATE function)
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Property [" + id + "] " + title + " - Rs." + price + " (" + status + ")";
    }
}
