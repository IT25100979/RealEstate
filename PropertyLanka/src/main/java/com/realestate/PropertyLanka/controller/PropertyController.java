package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.model.Property;
import com.realestate.PropertyLanka.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    private PropertyService service;

    @GetMapping("/")
    public String home() {
        return "Welcome to PropertyLanka!";
    }

    @PostMapping
    public String createNewProperty(@RequestBody Property property) {
        service.addProperty(property);
        return "Property created successfully!";
    }

    @GetMapping
    public List<Property> viewAllProperties() {
        return service.getAllProperties();
    }

    @PutMapping("/{id}")
    public String updatePropertyPrice(@PathVariable String id, @RequestParam double newPrice) {
        service.editPropertyPrice(id, newPrice);
        return "Property updated successfully!";
    }

    @DeleteMapping("/{id}")
    public String deleteProperty(@PathVariable String id) {
        service.removeProperty(id);
        return "Property deleted successfully!";
    }
}