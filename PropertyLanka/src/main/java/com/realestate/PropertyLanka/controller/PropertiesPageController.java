package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.model.Property;
import com.realestate.PropertyLanka.service.PropertyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

/**
 * PropertiesPageController
 *
 * Handles two pages:
 *   GET /properties  → shows all listings
 *   GET /search      → shows filtered results
 *
 * Added by Member 3 (Sachindu)
 */
@Controller
public class PropertiesPageController {

    private PropertyService propertyService = new PropertyService();

    // ── ALL PROPERTIES PAGE ──────────────────────────────
    @GetMapping("/properties")
    public String showPropertiesPage(Model model) {
        model.addAttribute("allProperties", propertyService.getAllProperties());
        return "properties";
    }

    // ── SEARCH PAGE ──────────────────────────────────────
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

        // If no filters provided — show empty search state
        boolean hasFilter = (keyword != null && !keyword.isEmpty())
                         || (address != null && !address.isEmpty())
                         || (propertyType != null && !propertyType.isEmpty())
                         || (status != null && !status.isEmpty())
                         || maxPrice != null;

        if (!hasFilter) {
            // No search yet — don't show results
            return "search";
        }

        // Apply filters
        List<Property> all = propertyService.getAllProperties();
        List<Property> results = all.stream()
            .filter(p -> matchesKeyword(p, keyword))
            .filter(p -> matchesAddress(p, address))
            .filter(p -> matchesType(p, propertyType))
            .filter(p -> matchesStatus(p, status))
            .filter(p -> matchesMaxPrice(p, maxPrice))
            .collect(Collectors.toList());

        // Sort
        if ("price_asc".equals(sort)) {
            results.sort((a, b) -> Double.compare(a.getPrice(), b.getPrice()));
        } else if ("price_desc".equals(sort)) {
            results.sort((a, b) -> Double.compare(b.getPrice(), a.getPrice()));
        }
        // "newest" = default order from file (newest added last → reverse)
        // already in insertion order from service

        model.addAttribute("results",     results);
        model.addAttribute("resultCount", results.size());

        return "search";
    }

    // ── PRIVATE FILTER HELPERS ───────────────────────────

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
