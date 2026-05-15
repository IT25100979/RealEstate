package com.realestate.PropertyLanka.service;

import com.realestate.PropertyLanka.model.Property;
import com.realestate.PropertyLanka.repository.PropertyRepository;
import java.util.ArrayList;
import java.util.List;

public class PropertyService {

    private PropertyRepository repository;

    public PropertyService() {
        this.repository = new PropertyRepository();
    }

    // ── CREATE ───────────────────────────────────────────
    public void addProperty(Property property) {
        if (property.getPrice() <= 0) {
            System.out.println("❌ Validation Error: Price must be greater than 0.");
            return;
        }
        repository.save(property);
        System.out.println("✅ Service: Property '" + property.getTitle() + "' saved successfully.");
    }

    // ── READ ALL ─────────────────────────────────────────
    public List<Property> getAllProperties() {
        return repository.findAll();
    }

    // ── READ BY ID ───────────────────────────────────────
    public Property getPropertyById(String id) {
        for (Property p : repository.findAll()) {
            if (p.getId().equals(id)) return p;
        }
        System.out.println("❌ Error: Property with ID " + id + " not found.");
        return null;
    }

    // ── READ BY SELLER ───────────────────────────────────
    // ← ADDED: filters properties by seller's ID
    public List<Property> getPropertiesBySellerId(String sellerId) {
        List<Property> result = new ArrayList<>();
        for (Property p : repository.findAll()) {
            if (p.getSellerId() != null && p.getSellerId().equals(sellerId)) {
                result.add(p);
            }
        }
        return result;
    }

    // ── UPDATE (full property) ───────────────────────────
    // ← ADDED: replaces the old editPropertyPrice with a full update
    public void updateProperty(Property updatedProperty) {
        repository.update(updatedProperty);
        System.out.println("✅ Service: Property updated successfully.");
    }

    // ── UPDATE (price only) — kept for backwards compat ──
    public void editPropertyPrice(String id, double newPrice) {
        Property p = getPropertyById(id);
        if (p != null) {
            p.setPrice(newPrice);
            repository.update(p);
            System.out.println("✅ Service: Property price updated successfully.");
        }
    }

    // ── DELETE ───────────────────────────────────────────
    public void removeProperty(String id) {
        if (getPropertyById(id) != null) {
            repository.delete(id);
            System.out.println("✅ Service: Property deleted successfully.");
        }
    }
}
