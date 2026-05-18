package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.model.Property;
import com.realestate.PropertyLanka.service.PropertyService;
import com.realestate.PropertyLanka.util.PriceAscComparator;
import com.realestate.PropertyLanka.util.PriceDescComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SearchController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/search")
    public String showSearchPage(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String propertyType,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "newest") String sort,
            Model model) {

        // Echo filter values back so form stays filled in
        model.addAttribute("keyword",      keyword);
        model.addAttribute("address",      address);
        model.addAttribute("propertyType", propertyType);
        model.addAttribute("status",       status);
        model.addAttribute("maxPrice",     maxPrice);
        model.addAttribute("sort",         sort);

        //  shows everything if none selected
        List<Property> results = propertyService.getAllProperties().stream()
            .filter(p -> matchesKeyword(p, keyword))
            .filter(p -> matchesAddress(p, address))
            .filter(p -> matchesType(p, propertyType))
            .filter(p -> matchesStatus(p, status))
            .filter(p -> matchesMaxPrice(p, maxPrice))
            .collect(Collectors.toList());

        // Sort
        if ("price_asc".equals(sort)) {
            results.sort(new PriceAscComparator());
        } else if ("price_desc".equals(sort)) {
            results.sort(new PriceDescComparator());
        } else {

            Collections.reverse(results);
        }

        model.addAttribute("results",     results);
        model.addAttribute("resultCount", results.size());

        return "search";
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
