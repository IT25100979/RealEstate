package com.realestate.PropertyLanka.model;

public class User {
    private String id;
    private String username;
    private String email;
    private String password; // In a real app, this would be encrypted!
    private String phone;
    private String address;
    private String role; // e.g., "Buyer", "Seller", "Admin"
    private String profilePicture;
    private String activeStatus; // e.g., "Active", "Banned", "Deleted"

    public User(String id, String username, String email, String password, String phone, String address, String role, String profilePicture, String activeStatus) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.profilePicture = profilePicture;
        this.activeStatus = activeStatus;
    }

    // Turns the object into a single line for the text file
    public String toDatabaseString() {
        return id + "|" + username + "|" + email + "|" + password + "|" +
                phone + "|" + address + "|" + role + "|" + profilePicture + "|" + activeStatus;
    }

    // Getters
    public String getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public String getActiveStatus() {
        return activeStatus;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    // Setters (For editing profile)
    public void setUsername(String username) { this.username = username; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAddress(String address) { this.address = address; }
    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }

    @Override
    public String toString() {
        return "User [" + role + "] " + username + " (" + email + ") - Status: " + activeStatus;
    }
}