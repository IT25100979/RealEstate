package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.model.Inquiry;
import com.realestate.PropertyLanka.model.Property;
import com.realestate.PropertyLanka.model.User;
import com.realestate.PropertyLanka.service.PropertyService;
import com.realestate.PropertyLanka.service.InquiryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DashboardController {

    private PropertyService propertyService = new PropertyService();
    private InquiryService inquiryService = new InquiryService();

    // --- SELLER DASHBOARD ---
    @GetMapping("/seller-dashboard")
    public String showSellerDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !user.getRole().equalsIgnoreCase("SELLER")) return "redirect:/login";

        model.addAttribute("user", user);

        // 1. Fetch Seller's Properties
        List<Property> sellerProperties = propertyService.getPropertiesBySellerId(user.getId());
        model.addAttribute("properties", sellerProperties);

        // 2. Fetch Inquiries for those specific properties
        List<Inquiry> sellerInquiries = new ArrayList<>();
        for (Property p : sellerProperties) {
            // Adds all inquiries for this specific property into our master list
            sellerInquiries.addAll(inquiryService.getInquiriesForProperty(p.getId()));
        }

        // 3. Send the inbox data to the UI
        model.addAttribute("inquiries", sellerInquiries);

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