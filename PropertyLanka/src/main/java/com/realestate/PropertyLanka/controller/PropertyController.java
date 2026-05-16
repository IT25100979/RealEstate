package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.model.Property;
import com.realestate.PropertyLanka.model.User;
import com.realestate.PropertyLanka.service.PropertyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    // --- 1. SHOW THE ADD PROPERTY FORM ---
    @GetMapping("/add-property")
    public String showAddPropertyForm(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !user.getRole().equalsIgnoreCase("SELLER")) {
            return "redirect:/login";
        }
        return "add-property";
    }

    // --- 2. SAVE NEW PROPERTY ---
    @PostMapping("/add-property")
    public String processAddProperty(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam double price,
            @RequestParam String address,
            @RequestParam String propertyType,
            @RequestParam String image,
            HttpSession session) {

        User seller = (User) session.getAttribute("loggedInUser");
        if (seller == null || !seller.getRole().equalsIgnoreCase("SELLER")) return "redirect:/login";

        String propertyId  = "P" + System.currentTimeMillis();
        String createdDate = LocalDate.now().toString();

        Property newProperty = new Property(
                propertyId, title, description, propertyType, "Sale",
                price, address, "", "", "",
                0, 0, 0.0, "Available", image, createdDate,
                seller.getUsername(), seller.getPhone(), seller.getEmail()
        );
        newProperty.setUserId(seller.getId());

        propertyService.addProperty(newProperty);
        System.out.println("✅ New Property Listed: " + title + " by Seller ID: " + seller.getId());

        return "redirect:/seller-dashboard";
    }

    // --- 3. SHOW EDIT FORM ---
    @GetMapping("/edit-property/{id}")
    public String showEditPropertyForm(@PathVariable String id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !user.getRole().equalsIgnoreCase("SELLER")) return "redirect:/login";

        Property property = propertyService.getPropertyById(id);

        if (property == null || !property.getUserId().equals(user.getId())) {
            System.out.println("🚨 Unauthorized edit attempt blocked!");
            return "redirect:/seller-dashboard";
        }

        model.addAttribute("property", property);
        return "edit-property";
    }

    // --- 4. SAVE EDITED PROPERTY ---
    @PostMapping("/edit-property")
    public String processEditProperty(
            @RequestParam String id,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam double price,
            @RequestParam String status,
            HttpSession session) {

        User seller = (User) session.getAttribute("loggedInUser");
        if (seller == null || !seller.getRole().equalsIgnoreCase("SELLER")) return "redirect:/login";

        Property existing = propertyService.getPropertyById(id);

        if (existing != null && existing.getUserId().equals(seller.getId())) {
            existing.setTitle(title);
            existing.setDescription(description);
            existing.setPrice(price);
            existing.setStatus(status);
            propertyService.addProperty(existing);
            System.out.println("✅ Property Updated: " + title);
        }

        return "redirect:/seller-dashboard";
    }

    // --- 5. DELETE PROPERTY ---
    @GetMapping("/delete-property/{id}")
    public String handleDeleteProperty(@PathVariable String id, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !user.getRole().equalsIgnoreCase("SELLER")) return "redirect:/login";

        Property property = propertyService.getPropertyById(id);

        if (property != null && property.getUserId().equals(user.getId())) {
            propertyService.removeProperty(id);
            System.out.println("🗑️ Property " + id + " deleted by Seller " + user.getUsername());
        } else {
            System.out.println("🚨 Unauthorized delete attempt blocked for Property ID: " + id);
        }

        return "redirect:/seller-dashboard";
    }

    // --- 6. VIEW PROPERTY DETAILS (PUBLIC) ---
    @GetMapping("/view-property/{id}")
    public String viewPropertyDetails(@PathVariable String id, HttpSession session, Model model) {
        Property property = propertyService.getPropertyById(id);
        if (property == null) return "redirect:/";

        model.addAttribute("property", property);
        model.addAttribute("user", session.getAttribute("loggedInUser"));

        return "view-property";
    }
}

