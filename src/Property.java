import jakarta.persistence.*;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ── BASIC INFO ──
    private String title;
    private String type;
    private String listingType;
    private double price;
    private String status;

    // ── LOCATION ──
    private String address;
    private String city;
    private String state;
    private String zip;

    // ── DETAILS ──
    private int bedrooms;
    private int bathrooms;
    private int area;

    private String description;

    // ── LISTER INFO ──
    private String listerName;
    private String listerPhone;
    private String listerEmail;

    // getters & setters

    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getListingType() { return listingType; }
    public void setListingType(String listingType) { this.listingType = listingType; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getZip() { return zip; }
    public void setZip(String zip) { this.zip = zip; }

    public int getBedrooms() { return bedrooms; }
    public void setBedrooms(int bedrooms) { this.bedrooms = bedrooms; }

    public int getBathrooms() { return bathrooms; }
    public void setBathrooms(int bathrooms) { this.bathrooms = bathrooms; }

    public int getArea() { return area; }
    public void setArea(int area) { this.area = area; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getListerName() { return listerName; }
    public void setListerName(String listerName) { this.listerName = listerName; }

    public String getListerPhone() { return listerPhone; }
    public void setListerPhone(String listerPhone) { this.listerPhone = listerPhone; }

    public String getListerEmail() { return listerEmail; }
    public void setListerEmail(String listerEmail) { this.listerEmail = listerEmail; }
}