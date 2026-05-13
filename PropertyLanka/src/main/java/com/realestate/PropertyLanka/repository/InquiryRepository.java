package com.realestate.PropertyLanka.repository;

import com.realestate.PropertyLanka.model.Inquiry;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InquiryRepository {
    private final String FILE_NAME = "PropertyLanka/src/main/resources/database/inquiries_db.txt";

    // 1. CREATE
    public void save(Inquiry inquiry) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(inquiry.toDatabaseString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving inquiry to database.");
        }
    }

    // 2. READ ALL
    public List<Inquiry> findAll() {
        List<Inquiry> inquiries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length == 6) {
                    inquiries.add(new Inquiry(data[0], data[1], data[2], data[3], data[4], data[5]));
                }
            }
        } catch (FileNotFoundException e) {
            // File doesn't exist yet, return empty list
        } catch (IOException e) {
            System.out.println("Error reading inquiries database.");
        }
        return inquiries;
    }

    // 3. UPDATE (For changing status to "Read")
    public void update(Inquiry updatedInquiry) {
        List<Inquiry> inquiries = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            for (Inquiry i : inquiries) {
                if (i.getId().equals(updatedInquiry.getId())) {
                    writer.write(updatedInquiry.toDatabaseString());
                } else {
                    writer.write(i.toDatabaseString());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating inquiries database.");
        }
    }
}