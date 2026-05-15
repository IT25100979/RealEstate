package com.realestate.PropertyLanka.model;

import jakarta.persistence.*;

@Entity
@Table(name = "inquiries")
public class Inquiry {

    @Id
    private String id;

    @Column(name = "property_id")
    private String propertyId;

    @Column(name = "buyer_id")
    private String buyerId;

    @Column(columnDefinition = "TEXT")
    private String message;

    private String date;
    private String status;

    public Inquiry() {}

    public Inquiry(String id, String propertyId, String buyerId,
                   String message, String date, String status) {
        this.id = id; this.propertyId = propertyId; this.buyerId = buyerId;
        this.message = message; this.date = date; this.status = status;
    }

    // Getters
    public String getId()         { return id; }
    public String getPropertyId() { return propertyId; }
    public String getBuyerId()    { return buyerId; }
    public String getMessage()    { return message; }
    public String getDate()       { return date; }
    public String getStatus()     { return status; }

    // Setter
    public void setStatus(String status) { this.status = status; }
}