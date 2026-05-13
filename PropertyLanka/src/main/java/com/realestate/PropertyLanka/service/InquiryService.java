package com.realestate.PropertyLanka.service;

import com.realestate.PropertyLanka.model.Inquiry;
import com.realestate.PropertyLanka.repository.InquiryRepository;
import java.util.ArrayList;
import java.util.List;

public class InquiryService {
    private InquiryRepository repository;

    public InquiryService() {
        this.repository = new InquiryRepository();
    }

    // BUYER SENDS INQUIRY
    public void sendInquiry(Inquiry inquiry) {
        if (inquiry.getMessage() == null || inquiry.getMessage().isEmpty()) {
            System.out.println("❌ Validation Error: Message cannot be empty.");
            return;
        }
        repository.save(inquiry);
        System.out.println("✅ Service: Inquiry sent successfully to the seller!");
    }

    // SELLER VIEWS INQUIRIES (Filtered by Property)
    public List<Inquiry> getInquiriesForProperty(String propertyId) {
        List<Inquiry> filteredList = new ArrayList<>();
        for (Inquiry i : repository.findAll()) {
            if (i.getPropertyId().equals(propertyId)) {
                filteredList.add(i);
            }
        }
        return filteredList;
    }

    // SELLER MARKS AS READ
    public void markAsRead(String inquiryId) {
        for (Inquiry i : repository.findAll()) {
            if (i.getId().equals(inquiryId)) {
                i.setStatus("Read");
                repository.update(i);
                System.out.println("✅ Service: Inquiry marked as Read.");
                return;
            }
        }
    }
}