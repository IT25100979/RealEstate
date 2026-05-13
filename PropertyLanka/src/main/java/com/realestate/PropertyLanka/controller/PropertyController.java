package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.model.Property;
import com.realestate.PropertyLanka.model.User;
import com.realestate.PropertyLanka.service.PropertyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class PropertyController {

    private PropertyService propertyService = new PropertyService();

    // --- 1. SHOW THE FORM ---
    @GetMapping("/add-property")
    public String showAddPropertyForm(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");

        // SECURITY: Only let Sellers see this page!
        if (user == null || !user.getRole().equalsIgnoreCase("SELLER")) {
            return "redirect:/login";
        }
        return "add-property"; // Looks for add-property.html
    }

    // --- 2. CATCH THE DATA & SAVE IT ---
    @PostMapping("/add-property")
    public String processAddProperty(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam double price,
            @RequestParam String address,
            @RequestParam String propertyType,
            @RequestParam String image, // Just a text link for now
            HttpSession session) {

        // 1. Get the seller who is currently logged in
        User seller = (User) session.getAttribute("loggedInUser");
        if (seller == null || !seller.getRole().equalsIgnoreCase("SELLER")) return "redirect:/login";

        // 2. Auto-generate the missing pieces
        String propertyId = "P" + System.currentTimeMillis(); // e.g., P1715600000000
        String sellerId = seller.getId(); // Grabbed securely from the session!
        String status = "Available"; // Brand new properties are always available
        String createdDate = LocalDate.now().toString(); // e.g., "2024-05-13"

        // 3. Assemble the 10-piece puzzle using your updated Property constructor
        Property newProperty = new Property(
                propertyId, sellerId, title, description, price,
                address, propertyType, status, image, createdDate
        );

        // 4. Save it using your Service
        propertyService.addProperty(newProperty);

        System.out.println("✅ New Property Listed: " + title + " by Seller ID: " + sellerId);

        // 5. Send them back to their dashboard so they can see their new listing
        return "redirect:/seller-dashboard";
    }
}