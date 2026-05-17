package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.model.Inquiry;
import com.realestate.PropertyLanka.model.User;
import com.realestate.PropertyLanka.service.InquiryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class InquiryController {

    private InquiryService inquiryService = new InquiryService();

    // --- CATCH THE MESSAGE & SAVE IT ---
    @PostMapping("/send-inquiry")
    public String processSendInquiry(
            @RequestParam String propertyId,
            @RequestParam String message,
            HttpSession session) {

        // 1. Get the currently logged-in user (The Sender)
        User buyer = (User) session.getAttribute("loggedInUser");

        // 2. SECURITY CHECK: Only active Buyers can send messages!
        if (buyer == null || !buyer.getRole().equalsIgnoreCase("BUYER")) {
            System.out.println("🚨 Blocked unauthorized inquiry attempt.");
            return "redirect:/login";
        }

        // 3. Auto-generate the tracking data
        String inquiryId = "INQ" + System.currentTimeMillis(); // e.g., INQ1715600000000
        String buyerId = buyer.getId();
        String date = LocalDate.now().toString();
        String status = "Unread"; // All new messages start as Unread

        // 4. Put the message in the "Envelope" (Your Inquiry Model)
        Inquiry newInquiry = new Inquiry(inquiryId, propertyId, buyerId, message, date, status);

        // 5. Hand it to the Service to save in the database
        inquiryService.sendInquiry(newInquiry);

        // 6. Redirect them back to the same property page so they don't lose their place
        return "redirect:/view-property/" + propertyId + "?success";
    }

    // --- MARK MESSAGE AS READ ---
    @GetMapping("/mark-read/{id}")
    public String markInquiryAsRead(@PathVariable String id, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");

        // Ensure only sellers can mark things as read
        if (user != null && user.getRole().equalsIgnoreCase("SELLER")) {
            inquiryService.markAsRead(id);
        }

        return "redirect:/seller-dashboard"; // Refresh the dashboard
    }
}