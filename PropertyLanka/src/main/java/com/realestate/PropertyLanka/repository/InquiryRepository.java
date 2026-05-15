package com.realestate.PropertyLanka.repository;

import com.realestate.PropertyLanka.model.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, String> {
    List<Inquiry> findByPropertyId(String propertyId);
}