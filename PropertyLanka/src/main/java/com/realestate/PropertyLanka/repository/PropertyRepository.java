package com.realestate.PropertyLanka.repository;

import com.realestate.PropertyLanka.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, String> {
    List<Property> findByUserId(String userId);
}