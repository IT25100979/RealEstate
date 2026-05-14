package com.realestate.PropertyLanka.repository;

import com.realestate.PropertyLanka.model.Property;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyRepository {
    private final String FILE_NAME = "src/main/resources/database/properties_db.txt";

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
                if (data.length == 19) {
                    Property p = new Property(
                            data[0],                        // id
                            data[1],                        // title
                            data[2],                        // description
                            data[3],                        // propertyType
                            data[4],                        // listingType
                            Double.parseDouble(data[5]),    // price
                            data[6],                        // address
                            data[7],                        // city
                            data[8],                        // state
                            data[9],                        // zip
                            Integer.parseInt(data[10]),     // bedrooms
                            Integer.parseInt(data[11]),     // bathrooms
                            Double.parseDouble(data[12]),   // area
                            data[13],                       // status
                            data[14],                       // image
                            data[15],                       // createdDate
                            data[16],                       // listerName
                            data[17],                       // listerPhone
                            data[18]                        // listerEmail
                    );
                    properties.add(p);
                }
            }
        } catch (FileNotFoundException e) {
            // Fine, no data yet
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