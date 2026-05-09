import com.propertyapp.model.Property;
import com.propertyapp.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PropertyController {

    @Autowired
    private PropertyRepository propertyRepository;

    // ADD PROPERTY (FROM HTML FORM)
    @PostMapping("/addProperty")
    public String addProperty(
            @RequestParam String title,
            @RequestParam String type,
            @RequestParam String listingType,
            @RequestParam double price,
            @RequestParam String status,

            @RequestParam String address,
            @RequestParam String city,
            @RequestParam String state,
            @RequestParam String zip,

            @RequestParam(required = false, defaultValue = "0") int bedrooms,
            @RequestParam(required = false, defaultValue = "0") int bathrooms,
            @RequestParam(required = false, defaultValue = "0") int area,

            @RequestParam(required = false) String description,

            @RequestParam String listerName,
            @RequestParam String listerPhone,
            @RequestParam String listerEmail
    ) {

        Property property = new Property();

        // Basic info
        property.setTitle(title);
        property.setType(type);
        property.setListingType(listingType);
        property.setPrice(price);
        property.setStatus(status);

        // Location
        property.setAddress(address);
        property.setCity(city);
        property.setState(state);
        property.setZip(zip);

        // Details
        property.setBedrooms(bedrooms);
        property.setBathrooms(bathrooms);
        property.setArea(area);
        property.setDescription(description);

        // Lister
        property.setListerName(listerName);
        property.setListerPhone(listerPhone);
        property.setListerEmail(listerEmail);

        // Save to DB
        propertyRepository.save(property);

        // redirect after success
        return "redirect:/index.html";
    }

    // VIEW ALL PROPERTIES
    @GetMapping("/properties")
    @ResponseBody
    public java.util.List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    //GET PROPERTY BY ID
    @GetMapping("/properties/{id}")
    @ResponseBody
    public Property getPropertyById(@PathVariable Long id) {
        return propertyRepository.findById(id).orElse(null);
    }

}