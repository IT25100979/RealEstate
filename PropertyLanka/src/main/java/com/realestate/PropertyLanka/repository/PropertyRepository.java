package com.realestate.PropertyLanka.repository;

import com.realestate.PropertyLanka.model.Property;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyRepository {
    // Make sure your path matches what we fixed in Phase 1!
    private final String FILE_NAME = "PropertyLanka/src/main/resources/database/properties_db.txt";

    // 1. CREATE
    public void save(Property property) {
        File file = new File(FILE_NAME);
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(property.toDatabaseString());
            writer.newLine();
            System.out.println("💾 PROPERTY SAVED: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("❌ Error saving property to database: " + e.getMessage());
        }
    }

    // 2. READ ALL
    public List<Property> findAll() {
        List<Property> properties = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) return properties; // Return empty list if no file exists yet

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");

                // 🚨 Changed to 10 to account for sellerId
                if (data.length == 10) {
                    Property p = new Property(
                            data[0], // id
                            data[1], // sellerId (NEW)
                            data[2], // title
                            data[3], // description
                            Double.parseDouble(data[4]), // price
                            data[5], // address
                            data[6], // propertyType
                            data[7], // status
                            data[8], // image
                            data[9]  // createdDate
                    );
                    properties.add(p);
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading property database: " + e.getMessage());
        }
        return properties;
    }

    // 3. UPDATE
    public void update(Property updatedProperty) {
        List<Property> properties = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            for (Property p : properties) {
                if (p.getId().equals(updatedProperty.getId())) {
                    writer.write(updatedProperty.toDatabaseString());
                } else {
                    writer.write(p.toDatabaseString());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Error updating property database.");
        }
    }

    // 4. DELETE
    public void delete(String id) {
        List<Property> properties = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            for (Property p : properties) {
                if (!p.getId().equals(id)) {
                    writer.write(p.toDatabaseString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error deleting property from database.");
        }
    }
}