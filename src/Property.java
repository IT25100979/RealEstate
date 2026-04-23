package Model;

public class Property{
        private int id;
        private String title;
        private String location;
        private double price;
        private String description;

        //constructors
        public Property(int id, String title, String location, double price, String description){
            this.id = id;
            this.title = title;
            this.location = location;
            this.price = price;
            this.description = description;
        }

        //getters and setters
        //getters
        public int getId(){
            return id;
        }
        public String getTitle(){
            return title;
        }
        public String getLocation(){
            return location;
        }
        public double getPrice(){
            return price;
        }
        public String getDescription(){
            return description;
        }

        //setters
        public void setTitle(String title){
            this.title = title;
        }
        public void setLocation(String location){
            this.location = location;
        }
        public void setPrice(double price){
            this.price = price;
        }
        public void setDescription(String description){
            this.description = description;
        }
}
