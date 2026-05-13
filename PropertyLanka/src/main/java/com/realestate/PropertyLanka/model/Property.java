package com.realestate.PropertyLanka.model;

public class Property {
    private String id;
    private String title;
    private String sellerId;
    private String description;
    private double price;
    private String address;
    private String propertyType;
    private String status;
    private String image; // Just a text link or file name for now (e.g., "house1.png")
    private String createdDate;

    public Property(String id, String sellerId, String title, String description, double price, String address, String propertyType, String status, String image, String createdDate) {
        this.id = id;
        this.sellerId = sellerId;
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
        return id + "|" + sellerId + "|" + title + "|" + description + "|" + price + "|" +
                address + "|" + propertyType + "|" + status + "|" + image + "|" + createdDate;
    }

    // Getters
    public String getId() { return id; }
    public String getSellerId() { return sellerId; }
    public String getTitle() { return title; }
    public double getPrice() { return price; }
    public String getStatus() { return status; }
    public String getCreatedDate() {
        return createdDate;
    }
    public String getDescription() { return description; } // Added for UI later
    public String getAddress() { return address; }         // Added for UI later
    public String getPropertyType() { return propertyType; } // Added for UI later
    public String getImage() { return image; }             // Added for UI later

    // Setters (Needed for the UPDATE function)
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Property [" + id + "] " + title + " - Rs." + price + " (" + status + ") - Owner: " + sellerId;
    }
}
