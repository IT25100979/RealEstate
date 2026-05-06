import com.propertyapp.model.Property;

import java.util.ArrayList;

public class PropertyRepository {

    private ArrayList<Property> properties = new ArrayList<>();

    // CREATE
    public boolean addProperty(Property p) {
        if (findById(p.getId()) != null) {
            return false; // duplicate ID
        }
        properties.add(p);
        return true;
    }

    // READ ALL
    public void getAllProperties() {
        for (Property p : properties) {
            p.display();
        }
    }

    // FIND BY ID
    public Property findById(String id) {
        for (Property p : properties) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    // UPDATE
    public boolean updateProperty(String id, String newLocation, double newPrice) {
        Property p = findById(id);
        if (p != null) {
            p.setLocation(newLocation);
            p.setPrice(newPrice);
            return true;
        }
        return false;
    }

    // DELETE
    public boolean deleteProperty(String id) {
        Property p = findById(id);
        if (p != null) {
            properties.remove(p);
            return true;
        }
        return false;
    }

    // CHECK EMPTY
    public boolean isEmpty() {
        return properties.isEmpty();
    }
}