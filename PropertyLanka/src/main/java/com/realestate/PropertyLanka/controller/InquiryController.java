package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.model.Inquiry;
import com.realestate.PropertyLanka.service.InquiryService;

import java.util.List;

public class InquiryController {
    private InquiryService service;

    public InquiryController() {
        this.service = new InquiryService();
    }

    // Buyer API
    public void submitInquiry(String id, String propertyId, String buyerId, String message, String date) {
        System.out.println("\n--- API: POST /inquiries ---");
        Inquiry newInquiry = new Inquiry(id, propertyId, buyerId, message, date, "Unread");
        service.sendInquiry(newInquiry);
    }

    // Seller API
    public void viewPropertyInquiries(String propertyId) {
        System.out.println("\n--- API: GET /inquiries/property/" + propertyId + " ---");
        List<Inquiry> list = service.getInquiriesForProperty(propertyId);

        if (list.isEmpty()) {
            System.out.println("No inquiries for property " + propertyId + " yet.");
        } else {
            for (Inquiry i : list) {
                System.out.println(i.toString());
            }
        }
    }
}