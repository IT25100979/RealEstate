package com.realestate.PropertyLanka.model;

public class Inquiry {
    private String id;
    private String propertyId; // The destination
    private String buyerId;    // The sender
    private String message;
    private String date;
    private String status;     // e.g., "Unread", "Read", "Responded"

    public Inquiry(String id, String propertyId, String buyerId, String message, String date, String status) {
        this.id = id;
        this.propertyId = propertyId;
        this.buyerId = buyerId;
        this.message = message;
        this.date = date;
        this.status = status;
    }

    public String toDatabaseString() {
        return id + "|" + propertyId + "|" + buyerId + "|" + message + "|" + date + "|" + status;
    }

    // Getters
    public String getId() { return id; }
    public String getPropertyId() { return propertyId; }
    public String getBuyerId() { return buyerId; }
    public String getMessage() { return message; }
    public String getStatus() { return status; }

    // Setter for updating status
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Inquiry [" + status + "] - For Property: " + propertyId + " | From Buyer: " + buyerId + "\nMessage: " + message;
    }
}