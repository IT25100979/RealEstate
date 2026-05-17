package com.realestate.PropertyLanka.model;

public class Property {

    // ── Fields ───────────────────────────────────────────
    private String id;
    private String sellerId;      // ← ADDED: links property to seller

    private String title;
    private String description;
    private String propertyType;
    private String listingType;

    private double price;

    private String address;
    private String city;
    private String state;
    private String zip;

    private int bedrooms;
    private int bathrooms;
    private double area;

    private String status;
    private String image;
    private String createdDate;

    private String listerName;
    private String listerPhone;
    private String listerEmail;

    //default constructor
    public Property() {
    }

    public Property(String id,
                    String title, String description, String propertyType, String listingType,
                    double price,
                    String address, String city, String state, String zip,
                    int bedrooms, int bathrooms, double area,
                    String status, String image, String createdDate,
                    String listerName, String listerPhone, String listerEmail) {
        this.id = id;

        this.title = title;
        this.description = description;
        this.propertyType = propertyType;
        this.listingType = listingType;

        this.price = price;

        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;

        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.area = area;

        this.status = status;
        this.image = image;
        this.createdDate = createdDate;

        this.listerName = listerName;
        this.listerPhone = listerPhone;
        this.listerEmail = listerEmail;
    }

    // ── File handling ────────────────────────────────────
    // Format: id|sellerId|title|description|price|address|propertyType|status|image|createdDate
    public String toDatabaseString() {
        return id + "|" + title + "|" + description + "|" + propertyType + "|" + listingType + "|" +
                price + "|" +
                address + "|" + city + "|" + state + "|" + zip + "|" +
                bedrooms + "|" + bathrooms + "|" + area + "|" +
                status + "|" + image + "|" + createdDate + "|" +
                listerName + "|" + listerPhone + "|" + listerEmail;
    }

    // Getters
    public String getId() { return id; }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPropertyType() { return propertyType; }
    public String getListingType() { return listingType; }

    public double getPrice() { return price; }

    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getZip() { return zip; }

    public int getBedrooms() { return bedrooms; }
    public int getBathrooms() { return bathrooms; }
    public double getArea() { return area; }

    public String getStatus() { return status; }
    public String getImage() { return image; }
    public String getCreatedDate() { return createdDate; }

    public String getListerName() { return listerName; }
    public String getListerPhone() { return listerPhone; }
    public String getListerEmail() { return listerEmail; }

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
        return "Property{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + propertyType + '\'' +
                ", listingType='" + listingType + '\'' +
                ", status='" + status + '\'' +
                ", price=" + price +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", bedrooms=" + bedrooms +
                ", bathrooms=" + bathrooms +
                ", area=" + area +
                ", description='" + description + '\'' +
                ", listerName='" + listerName + '\'' +
                ", listerPhone='" + listerPhone + '\'' +
                ", listerEmail='" + listerEmail + '\'' +
                '}';
    }
}
