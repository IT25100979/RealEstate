package com.realestate.PropertyLanka.repository;

import com.realestate.PropertyLanka.model.Property;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyRepository {
    private final String FILE_NAME = "src/common/database/properties_db.txt";

    // 1. CREATE
    public void save(Property property) {
        // FileWriter(..., true) appends to the end of the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(property.toDatabaseString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving to database: " + e.getMessage());
        }
    }

    // 2. READ ALL
    public List<Property> findAll() {
        List<Property> properties = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length == 9) {
                    Property p = new Property(data[0], data[1], data[2], Double.parseDouble(data[3]),
                            data[4], data[5], data[6], data[7], data[8]);
                    properties.add(p);
                }
            }
        } catch (FileNotFoundException e) {
            // File doesn't exist yet, which is fine! Just return the empty list.
        } catch (IOException e) {
            System.out.println("Error reading database: " + e.getMessage());
        }
        return properties;
    }

    // 3. UPDATE
    public void update(Property updatedProperty) {
        List<Property> properties = findAll(); // Get all properties
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            // false = Overwrite the entire file
            for (Property p : properties) {
                if (p.getId().equals(updatedProperty.getId())) {
                    writer.write(updatedProperty.toDatabaseString()); // Write the new data
                } else {
                    writer.write(p.toDatabaseString()); // Keep the old data
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating database.");
        }
    }

    // 4. DELETE
    public void delete(String id) {
        List<Property> properties = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            for (Property p : properties) {
                if (!p.getId().equals(id)) { // Only write back the ones that DO NOT match the ID
                    writer.write(p.toDatabaseString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error deleting from database.");
        }
    }
}