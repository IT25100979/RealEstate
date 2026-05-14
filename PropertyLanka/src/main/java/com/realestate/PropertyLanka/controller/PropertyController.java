package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.model.Property;
import com.realestate.PropertyLanka.service.PropertyService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")

public class PropertyController {
    private PropertyService service;

    public PropertyController() {
        this.service = new PropertyService();
    }

    //HOME MESSAGE
    @GetMapping("/")
    public String home() {
        return "Welcome to PropertyLanka!";
    }

    //CREATE NEW PROPERTY
    @PostMapping
    public String createNewProperty(@RequestBody Property property) {
        System.out.println("\n--- API: POST /properties ---");
        service.addProperty(property);
        return "Property created successfully!";
    }
    /*public void createNewProperty(String id,
                                  String title, String description, String propertyType, String listingType,
                                  double price,
                                  String address, String city, String state, String zip,
                                  int bedrooms, int bathrooms, double area,
                                  String status, String image, String createdDate,
                                  String listerName, String listerPhone, String listerEmail) {
        System.out.println("\n--- API: POST /properties ---");
        Property newProp = new Property (id, title, description, propertyType, listingType, price, address, city, state, zip, bedrooms, bathrooms, area, status, image, createdDate, listerName, listerPhone, listerEmail);
        service.addProperty(newProp);
    }*/

    //VIEW ALL PROPERTIES
    @GetMapping
    public List<Property> viewAllProperties() {
        System.out.println("\n--- API: GET /properties ---");
        return service.getAllProperties();
    }
    /*public void viewAllProperties() {
        System.out.println("\n--- API: GET /properties ---");
        List<Property> list = service.getAllProperties();
        if (list.isEmpty()) {
            System.out.println("No properties found.");
        } else {
            for (Property p : list) {
                System.out.println(p.toString());
            }
        }
    }*/

    //UPDATE PROPERTY PRICE
    @PutMapping("/{id}")
    public String updatePropertyPrice(@PathVariable String id, @RequestParam double newPrice) {
        System.out.println("\n--- API: PUT /properties/" + id + " ---");
        service.editPropertyPrice(id, newPrice);
        return "Property updated successfully!";
    }
    /*public void updatePropertyPrice(String id, double newPrice) {
        System.out.println("\n--- API: PUT /properties/" + id + " ---");
        service.editPropertyPrice(id, newPrice);
    }*/

    //DELETE PROPERTY
    @DeleteMapping("/{id}")
    public String deleteProperty(@PathVariable String id) {
        System.out.println("\n--- API: DELETE /properties/" + id + " ---");
        service.removeProperty(id);
        return "Property deleted successfully!";
    }
    /*public void deleteProperty(String id) {
        System.out.println("\n--- API: DELETE /properties/" + id + " ---");
        service.removeProperty(id);
    }*/
}