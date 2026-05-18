package com.realestate.PropertyLanka.model;

import jakarta.persistence.*;

@Entity
@Table(name = "properties")
public class Property {

    @Id
    private String id;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "property_type")
    private String propertyType;

    @Column(name = "listing_type")
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

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "lister_name")
    private String listerName;

    @Column(name = "lister_phone")
    private String listerPhone;

    @Column(name = "lister_email")
    private String listerEmail;

    @Column(name = "user_id")
    private String userId;

    public Property() {}

    public Property(String id, String title, String description,
                    String propertyType, String listingType, double price,
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

    public String getId()            { return id; }
    public String getTitle()         { return title; }
    public String getDescription()   { return description; }
    public String getPropertyType()  { return propertyType; }
    public String getListingType()   { return listingType; }
    public double getPrice()         { return price; }
    public String getAddress()       { return address; }
    public String getCity()          { return city; }
    public String getState()         { return state; }
    public String getZip()           { return zip; }
    public int getBedrooms()         { return bedrooms; }
    public int getBathrooms()        { return bathrooms; }
    public double getArea()          { return area; }
    public String getStatus()        { return status; }
    public String getImage()         { return image; }
    public String getCreatedDate()   { return createdDate; }
    public String getListerName()    { return listerName; }
    public String getListerPhone()   { return listerPhone; }
    public String getListerEmail()   { return listerEmail; }
    public String getUserId()        { return userId; }

    public void setTitle(String title)             { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price)             { this.price = price; }
    public void setStatus(String status)           { this.status = status; }
    public void setUserId(String userId)           { this.userId = userId; }
}