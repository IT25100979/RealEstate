package com.realestate.PropertyLanka.model;

public class Property {

    // ── Fields ───────────────────────────────────────────
    private String id;
    private String sellerId;      // ← ADDED: links property to seller
    private String title;
    private String description;
    private double price;
    private String address;
    private String propertyType;
    private String status;
    private String image;
    private String createdDate;

    // ── Constructor (10 params) ──────────────────────────
    public Property(String id, String sellerId, String title, String description,
                    double price, String address, String propertyType,
                    String status, String image, String createdDate) {
        this.id           = id;
        this.sellerId     = sellerId;
        this.title        = title;
        this.description  = description;
        this.price        = price;
        this.address      = address;
        this.propertyType = propertyType;
        this.status       = status;
        this.image        = image;
        this.createdDate  = createdDate;
    }

    // ── File handling ────────────────────────────────────
    // Format: id|sellerId|title|description|price|address|propertyType|status|image|createdDate
    public String toDatabaseString() {
        return id + "|" + sellerId + "|" + title + "|" + description + "|" + price + "|" +
               address + "|" + propertyType + "|" + status + "|" + image + "|" + createdDate;
    }

    // ── Getters ──────────────────────────────────────────
    public String getId()           { return id; }
    public String getSellerId()     { return sellerId; }
    public String getTitle()        { return title; }
    public String getDescription()  { return description; }
    public double getPrice()        { return price; }
    public String getAddress()      { return address; }
    public String getPropertyType() { return propertyType; }
    public String getStatus()       { return status; }
    public String getImage()        { return image; }
    public String getCreatedDate()  { return createdDate; }

    // ── Setters ──────────────────────────────────────────
    public void setTitle(String title)             { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price)             { this.price = price; }
    public void setAddress(String address)         { this.address = address; }
    public void setPropertyType(String type)       { this.propertyType = type; }
    public void setStatus(String status)           { this.status = status; }
    public void setImage(String image)             { this.image = image; }

    @Override
    public String toString() {
        return "Property [" + id + "] " + title + " - Rs." + price + " (" + status + ")";
    }
}
