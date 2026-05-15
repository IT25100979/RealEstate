package com.realestate.PropertyLanka.repository;

import com.realestate.PropertyLanka.model.Property;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyRepository {

    private final String FILE_NAME = "src/main/resources/database/properties_db.txt";

    // 1. CREATE
    public void save(Property property) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(property.toDatabaseString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving to database: " + e.getMessage());
        }
    }

    // 2. READ ALL
    // Now reads 10 fields: id|sellerId|title|description|price|address|propertyType|status|image|createdDate
    public List<Property> findAll() {
        List<Property> properties = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split("\\|", -1);
                if (data.length >= 10) {
                    Property p = new Property(
                        data[0],                       // id
                        data[1],                       // sellerId   ← NEW
                        data[2],                       // title
                        data[3],                       // description
                        Double.parseDouble(data[4]),   // price
                        data[5],                       // address
                        data[6],                       // propertyType
                        data[7],                       // status
                        data[8],                       // image
                        data[9]                        // createdDate
                    );
                    properties.add(p);
                }
            }
        } catch (FileNotFoundException e) {
            // File doesn't exist yet — return empty list
        } catch (IOException e) {
            System.out.println("Error reading database: " + e.getMessage());
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
            System.out.println("Error updating database: " + e.getMessage());
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
            System.out.println("Error deleting from database: " + e.getMessage());
        }
    }
}
