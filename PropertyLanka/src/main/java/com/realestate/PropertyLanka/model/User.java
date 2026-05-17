package com.realestate.PropertyLanka.model;

public class User {

    // ── Fields ───────────────────────────────────────────
    private String id;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String role;
    private String profilePicture;
    private String activeStatus;

    // ── Constructor ──────────────────────────────────────
    public User(String id, String username, String email, String password,
                String phone, String address, String role,
                String profilePicture, String activeStatus) {
        this.id             = id;
        this.username       = username;
        this.email          = email;
        this.password       = password;
        this.phone          = phone;
        this.address        = address;
        this.role           = role;
        this.profilePicture = profilePicture;
        this.activeStatus   = activeStatus;
    }

    // ── File handling ────────────────────────────────────
    public String toDatabaseString() {
        return id + "|" + username + "|" + email + "|" + password + "|" +
               phone + "|" + address + "|" + role + "|" + profilePicture + "|" + activeStatus;
    }

    // ── Getters ──────────────────────────────────────────
    public String getId()             { return id; }
    public String getUsername()       { return username; }
    public String getEmail()          { return email; }
    public String getPassword()       { return password; }
    public String getPhone()          { return phone; }          // ← ADDED
    public String getAddress()        { return address; }        // ← ADDED
    public String getRole()           { return role; }
    public String getProfilePicture() { return profilePicture; }
    public String getActiveStatus()   { return activeStatus; }   // ← ADDED

    // ── Setters ──────────────────────────────────────────
    public void setUsername(String username)             { this.username = username; }
    public void setPhone(String phone)                   { this.phone = phone; }
    public void setAddress(String address)               { this.address = address; }
    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }

    @Override
    public String toString() {
        return "User [" + role + "] " + username + " (" + email + ") - Status: " + activeStatus;
    }
}
