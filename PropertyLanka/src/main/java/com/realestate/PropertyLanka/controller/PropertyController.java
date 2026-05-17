package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.model.Property;
import com.realestate.PropertyLanka.model.User;
import com.realestate.PropertyLanka.service.PropertyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RestController
@RequestMapping("/properties")

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
    //HOME MESSAGE
    @GetMapping("/")
    public String home() {
        return "Welcome to PropertyLanka!";
    }

    //CREATE NEW PROPERTY
    @PostMapping
    public String createNewProperty(@RequestBody Property property) {
        System.out.println("\n--- API: POST /properties ---");
        service.addProperty(property);
        return "Property created successfully!";
    }
    /*public void createNewProperty(String id,
                                  String title, String description, String propertyType, String listingType,
                                  double price,
                                  String address, String city, String state, String zip,
                                  int bedrooms, int bathrooms, double area,
                                  String status, String image, String createdDate,
                                  String listerName, String listerPhone, String listerEmail) {
        System.out.println("\n--- API: POST /properties ---");
        Property newProp = new Property (id, title, description, propertyType, listingType, price, address, city, state, zip, bedrooms, bathrooms, area, status, image, createdDate, listerName, listerPhone, listerEmail);
        service.addProperty(newProp);
    }*/

    // --- 3. SHOW THE EDIT FORM ---
    @GetMapping("/edit-property/{id}")
    public String showEditPropertyForm(@PathVariable String id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !user.getRole().equalsIgnoreCase("SELLER")) return "redirect:/login";

        Property property = propertyService.getPropertyById(id);

        // SECURITY CHECK: Does this property exist, and does THIS seller actually own it?
        if (property == null || !property.getSellerId().equals(user.getId())) {
            System.out.println("🚨 Unauthorized edit attempt blocked!");
            return "redirect:/seller-dashboard";
        }

        // Pass the existing property data to the HTML
        model.addAttribute("property", property);
        return "edit-property";
    }

    // --- 4. CATCH THE DATA & UPDATE IT ---
    @PostMapping("/edit-property")
    public String processEditProperty(
            @RequestParam String id, // We need the ID to know which one to overwrite
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam double price,
            @RequestParam String address,
            @RequestParam String propertyType,
            @RequestParam String status,
            @RequestParam String image,
            HttpSession session) {

        User seller = (User) session.getAttribute("loggedInUser");
        if (seller == null || !seller.getRole().equalsIgnoreCase("SELLER")) return "redirect:/login";

        // Fetch the old property first so we can keep the original "createdDate"
        Property existingProperty = propertyService.getPropertyById(id);

        if (existingProperty != null && existingProperty.getSellerId().equals(seller.getId())) {

            // Build the updated object
            Property updatedProperty = new Property(
                    id, seller.getId(), title, description, price,
                    address, propertyType, status, image, existingProperty.getCreatedDate()
            );

            // Send to database
            propertyService.updateProperty(updatedProperty);
            System.out.println("✅ Property Updated: " + title);
        }

        return "redirect:/seller-dashboard";
    }

    // --- 5. HANDLE DELETE PROPERTY ---
    @GetMapping("/delete-property/{id}")
    public String handleDeleteProperty(@PathVariable String id, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");

        // 1. Security: Are they logged in as a Seller?
        if (user == null || !user.getRole().equalsIgnoreCase("SELLER")) {
            return "redirect:/login";
        }

        Property property = propertyService.getPropertyById(id);

        // 2. Security: Does the property exist and belong to THIS seller?
        if (property != null && property.getSellerId().equals(user.getId())) {
            propertyService.removeProperty(id);
            System.out.println("🗑️ Property " + id + " deleted by Seller " + user.getUsername());
        } else {
            System.out.println("🚨 Unauthorized delete attempt blocked for Property ID: " + id);
        }

        // 3. Refresh the dashboard
        return "redirect:/seller-dashboard";
    //VIEW ALL PROPERTIES
    @GetMapping
    public List<Property> viewAllProperties() {
        System.out.println("\n--- API: GET /properties ---");
        return service.getAllProperties();
    }
    /*public void viewAllProperties() {
        System.out.println("\n--- API: GET /properties ---");
        List<Property> list = service.getAllProperties();
        if (list.isEmpty()) {
            System.out.println("No properties found.");
        } else {
            for (Property p : list) {
                System.out.println(p.toString());
            }
        }
    }*/

    //UPDATE PROPERTY PRICE
    @PutMapping("/{id}")
    public String updatePropertyPrice(@PathVariable String id, @RequestParam double newPrice) {
        System.out.println("\n--- API: PUT /properties/" + id + " ---");
        service.editPropertyPrice(id, newPrice);
        return "Property updated successfully!";
    }
    /*public void updatePropertyPrice(String id, double newPrice) {
        System.out.println("\n--- API: PUT /properties/" + id + " ---");
        service.editPropertyPrice(id, newPrice);
    }*/

    // --- 6. VIEW PROPERTY DETAILS (PUBLIC) ---
    @GetMapping("/view-property/{id}")
    public String viewPropertyDetails(@PathVariable String id, HttpSession session, Model model) {
        // 1. Fetch the specific property
        Property property = propertyService.getPropertyById(id);

        // If someone types a fake ID, send them home
        if (property == null) {
            return "redirect:/";
        }

        // 2. Pass the property to the HTML
        model.addAttribute("property", property);

        // 3. Pass the logged-in user so Thymeleaf knows whether to show the Contact Form
        model.addAttribute("user", session.getAttribute("loggedInUser"));

        return "view-property"; // Looks for view-property.html
    //DELETE PROPERTY
    @DeleteMapping("/{id}")
    public String deleteProperty(@PathVariable String id) {
        System.out.println("\n--- API: DELETE /properties/" + id + " ---");
        service.removeProperty(id);
        return "Property deleted successfully!";
    }
    /*public void deleteProperty(String id) {
        System.out.println("\n--- API: DELETE /properties/" + id + " ---");
        service.removeProperty(id);
    }*/
}