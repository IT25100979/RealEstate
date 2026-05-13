package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.model.Property;
import com.realestate.PropertyLanka.service.PropertyService;

import java.util.List;

public class PropertyController {
    private PropertyService service;

    public PropertyController() {
        this.service = new PropertyService();
    }

    public void createNewProperty(String id,
                                  String title, String description, String propertyType, String listingType,
                                  double price,
                                  String address, String city, String state, String zip,
                                  int bedrooms, int bathrooms, double area,
                                  String status, String image, String createdDate,
                                  String listerName, String listerPhone, String listerEmail) {
        System.out.println("\n--- API: POST /properties ---");
        Property newProp = new Property (id, title, description, propertyType, listingType, price, address, city, state, zip, bedrooms, bathrooms, area, status, image, createdDate, listerName, listerPhone, listerEmail);
        service.addProperty(newProp);
    }

    public void viewAllProperties() {
        System.out.println("\n--- API: GET /properties ---");
        List<Property> list = service.getAllProperties();
        if (list.isEmpty()) {
            System.out.println("No properties found.");
        } else {
            for (Property p : list) {
                System.out.println(p.toString());
            }
        }
    }

    public void updatePropertyPrice(String id, double newPrice) {
        System.out.println("\n--- API: PUT /properties/" + id + " ---");
        service.editPropertyPrice(id, newPrice);
    }

    public void deleteProperty(String id) {
        System.out.println("\n--- API: DELETE /properties/" + id + " ---");
        service.removeProperty(id);
    }
}