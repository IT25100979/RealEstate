package com.realestate.PropertyLanka.service;

import com.realestate.PropertyLanka.model.Property;
import com.realestate.PropertyLanka.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PropertyService {

    @Autowired
    private PropertyRepository repository;

    public void addProperty(Property property) {
        if (property.getPrice() <= 0) {
            System.out.println("❌ Validation Error: Price must be > 0.");
            return;
        }
        repository.save(property);
    }
    public List<Property> getPropertiesBySellerId(String userId) {
        return repository.findAll().stream()
                .filter(p -> userId.equals(p.getUserId()))
                .collect(java.util.stream.Collectors.toList());
    }

    public List<Property> getAllProperties() {
        return repository.findAll();
    }

    public Property getPropertyById(String id) {
        return repository.findById(id).orElse(null);
    }

    public void editPropertyPrice(String id, double newPrice) {
        Property p = getPropertyById(id);
        if (p != null) {
            p.setPrice(newPrice);
            repository.save(p);
        }
    }

    public void removeProperty(String id) {
        repository.deleteById(id);
    }
}