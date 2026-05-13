package com.realestate.PropertyLanka.service;

import com.realestate.PropertyLanka.model.Property;
import com.realestate.PropertyLanka.repository.PropertyRepository;
import java.util.List;

public class PropertyService {
    private PropertyRepository repository;

    public PropertyService() {
        this.repository = new PropertyRepository();
    }

    // CREATE
    public void addProperty(Property property) {
        if (property.getPrice() <= 0) {
            System.out.println("❌ Validation Error: Price must be greater than 0.");
            return;
        }
        repository.save(property);
        System.out.println("✅ Service: Property '" + property.getTitle() + "' saved successfully.");
    }

    // READ
    public List<Property> getAllProperties() {
        return repository.findAll();
    }

    public Property getPropertyById(String id) {
        for (Property p : repository.findAll()) {
            if (p.getId().equals(id)) return p;
        }
        System.out.println("❌ Error: Property with ID " + id + " not found.");
        return null;
    }

    // UPDATE
    public void editPropertyPrice(String id, double newPrice) {
        Property p = getPropertyById(id);
        if (p != null) {
            p.setPrice(newPrice);
            repository.update(p);
            System.out.println("✅ Service: Property updated successfully.");
        }
    }

    // DELETE
    public void removeProperty(String id) {
        if (getPropertyById(id) != null) {
            repository.delete(id);
            System.out.println("✅ Service: Property deleted successfully.");
        }
    }
}