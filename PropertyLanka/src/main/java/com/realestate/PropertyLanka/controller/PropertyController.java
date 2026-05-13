package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.model.Property;
import com.realestate.PropertyLanka.service.PropertyService;

import java.util.List;

public class PropertyController {
    private PropertyService service;

    public PropertyController() {
        this.service = new PropertyService();
    }

    public void createNewProperty(String id, String title, String desc, double price, String address, String type, String status, String image, String date) {
        System.out.println("\n--- API: POST /properties ---");
        Property newProp = new Property(id, title, desc, price, address, type, status, image, date);
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