package com.realestate.PropertyLanka.repository;

import com.realestate.PropertyLanka.model.Property;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyRepository {

    private String getFileName() {
        if (new File("PropertyLanka/src/main/resources/database/properties_db.txt").exists()) {
            return "PropertyLanka/src/main/resources/database/properties_db.txt";
        }
        return "src/main/resources/database/properties_db.txt";
    }

    // 1. CREATE
    public void save(Property property) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFileName(), true))) {
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
        try (BufferedReader reader = new BufferedReader(new FileReader(getFileName()))) {
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
        List<Property> properties = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFileName(), false))) {
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFileName(), false))) {
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
