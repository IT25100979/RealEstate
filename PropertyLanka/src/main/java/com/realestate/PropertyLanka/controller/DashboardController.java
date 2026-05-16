package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.model.Inquiry;
import com.realestate.PropertyLanka.model.Property;
import com.realestate.PropertyLanka.model.User;
import com.realestate.PropertyLanka.service.InquiryService;
import com.realestate.PropertyLanka.service.PropertyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private InquiryService inquiryService;

    // --- SELLER DASHBOARD ---
    @GetMapping("/seller-dashboard")
    public String showSellerDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !user.getRole().equalsIgnoreCase("SELLER")) return "redirect:/login";

        model.addAttribute("user", user);

        List<Property> sellerProperties = propertyService.getPropertiesBySellerId(user.getId());
        model.addAttribute("properties", sellerProperties);

        List<Inquiry> sellerInquiries = new ArrayList<>();
        for (Property p : sellerProperties) {
            sellerInquiries.addAll(inquiryService.getInquiriesForProperty(p.getId()));
        }
        model.addAttribute("inquiries", sellerInquiries);

        return "seller-dashboard";
    }

    // --- SHARED PROFILE (Buyer & Seller) ---
    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        model.addAttribute("user", user);
        return "profile";
    }
}
