package com.realestate.PropertyLanka.service;

import com.realestate.PropertyLanka.model.Inquiry;
import com.realestate.PropertyLanka.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class InquiryService {

    @Autowired
    private InquiryRepository repository;

    public void sendInquiry(Inquiry inquiry) {
        if (inquiry.getMessage() == null || inquiry.getMessage().isEmpty()) {
            System.out.println("❌ Message cannot be empty.");
            return;
        }
        repository.save(inquiry);
    }

    public List<Inquiry> getInquiriesForProperty(String propertyId) {
        return repository.findByPropertyId(propertyId);
    }

    public List<Inquiry> getInquiriesForProperties(List<String> propertyIds) {
        if (propertyIds == null || propertyIds.isEmpty()) return new ArrayList<>();
        return repository.findByPropertyIdIn(propertyIds);
    }

    public void markAsRead(String inquiryId) {
        repository.findById(inquiryId).ifPresent(i -> {
            i.setStatus("Read");
            repository.save(i);
        });
    }
}