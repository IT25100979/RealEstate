package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.model.User;
import com.realestate.PropertyLanka.service.PropertyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private PropertyService propertyService = new PropertyService();

    // --- SELLER DASHBOARD ---
    @GetMapping("/seller-dashboard")
    public String showSellerDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !user.getRole().equalsIgnoreCase("SELLER")) return "redirect:/login";

        model.addAttribute("user", user);
        // Note: For now this loads ALL properties. We will filter this by sellerId in Phase 3!
        model.addAttribute("properties", propertyService.getAllProperties());

        return "seller-dashboard";
    }

    // --- SHARED PROFILE (Buyer & Seller) ---
    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        model.addAttribute("user", user);
        return "profile"; // Looks for profile.html
    }
}