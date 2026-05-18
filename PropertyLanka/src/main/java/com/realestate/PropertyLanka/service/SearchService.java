package com.realestate.PropertyLanka.service;

import com.realestate.PropertyLanka.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    private PropertyService propertyService;

    public List<Property> searchProperties(
            String keyword,
            String address,
            String propertyType,
            String status,
            Double maxPrice) {

        List<Property> all = propertyService.getAllProperties();

        return all.stream()
                .filter(p -> matchesKeyword(p, keyword))
                .filter(p -> matchesAddress(p, address))
                .filter(p -> matchesType(p, propertyType))
                .filter(p -> matchesStatus(p, status))
                .filter(p -> matchesMaxPrice(p, maxPrice))
                .collect(Collectors.toList());
    }

    private boolean matchesKeyword(Property p, String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) return true;
        String kw = keyword.trim().toLowerCase();
        return (p.getTitle()       != null && p.getTitle().toLowerCase().contains(kw))
            || (p.getDescription() != null && p.getDescription().toLowerCase().contains(kw))
            || (p.getAddress()     != null && p.getAddress().toLowerCase().contains(kw));
    }

    private boolean matchesAddress(Property p, String address) {
        if (address == null || address.trim().isEmpty()) return true;
        return p.getAddress() != null &&
               p.getAddress().toLowerCase().contains(address.trim().toLowerCase());
    }

    private boolean matchesType(Property p, String propertyType) {
        if (propertyType == null || propertyType.trim().isEmpty()) return true;
        return p.getPropertyType() != null &&
               p.getPropertyType().equalsIgnoreCase(propertyType.trim());
    }

    private boolean matchesStatus(Property p, String status) {
        if (status == null || status.trim().isEmpty()) return true;
        return p.getStatus() != null &&
               p.getStatus().equalsIgnoreCase(status.trim());
    }

    private boolean matchesMaxPrice(Property p, Double maxPrice) {
        if (maxPrice == null) return true;
        return p.getPrice() <= maxPrice;
    }
}
