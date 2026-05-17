package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.service.PropertyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * PropertiesPageController
 *
 * Handles showing all properties.
 * Search functionality has been moved to SearchController.
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
}
