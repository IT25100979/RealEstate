public class Property {
    private String id;
    private String location;
    private double price;

    public Property(String id, String location, double price) {
        this.id = id;
        this.location = location;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public double getPrice() {
        return price;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void display() {
        System.out.println(id + " | " + location + " | " + price);
    }
}