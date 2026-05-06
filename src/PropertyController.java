import com.propertyapp.model.Property;
import com.propertyapp.repository.PropertyRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private PropertyRepository repo = new PropertyRepository();

    // GET ALL
    @GetMapping
    public List<Property> getAll() {
        return repo.getAllPropertiesList();
    }

    // ADD
    @PostMapping
    public String add(@RequestBody Property p) {
        boolean added = repo.addProperty(p);
        return added ? "Added successfully" : "ID already exists";
    }

    // UPDATE
    @PutMapping("/{id}")
    public String update(@PathVariable String id, @RequestBody Property p) {
        boolean updated = repo.updateProperty(id, p.getLocation(), p.getPrice());
        return updated ? "Updated successfully" : "Not found";
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        boolean deleted = repo.deleteProperty(id);
        return deleted ? "Deleted successfully" : "Not found";
    }
}