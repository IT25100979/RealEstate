package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.model.Inquiry;
import com.realestate.PropertyLanka.model.User;
import com.realestate.PropertyLanka.service.InquiryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    // --- BUYER SENDS INQUIRY ---
    @PostMapping("/send-inquiry")
    public String processSendInquiry(
            @RequestParam String propertyId,
            @RequestParam String message,
            HttpSession session) {

        User buyer = (User) session.getAttribute("loggedInUser");

        if (buyer == null || !buyer.getRole().equalsIgnoreCase("BUYER")) {
            System.out.println("🚨 Blocked unauthorized inquiry attempt.");
            return "redirect:/login";
        }

        String inquiryId = "INQ" + System.currentTimeMillis();
        String date      = LocalDate.now().toString();

        Inquiry newInquiry = new Inquiry(inquiryId, propertyId, buyer.getId(), message, date, "Unread");
        inquiryService.sendInquiry(newInquiry);

        return "redirect:/view-property/" + propertyId + "?success";
    }

    // --- SELLER MARKS INQUIRY AS READ ---
    @GetMapping("/mark-read/{id}")
    public String markInquiryAsRead(@PathVariable String id, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");

        if (user != null && user.getRole().equalsIgnoreCase("SELLER")) {
            inquiryService.markAsRead(id);
        }

        return "redirect:/seller-dashboard";
    }
}
