package com.realestate.PropertyLanka.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String role;

    @Column(name = "profile_pic")
    private String profilePicture;

    @Column(name = "status")
    private String activeStatus;

    public User() {}

    public User(String id, String username, String email, String password,
                String phone, String address, String role,
                String profilePicture, String activeStatus) {
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

    // Getters
    public String getId()             { return id; }
    public String getUsername()       { return username; }
    public String getEmail()          { return email; }
    public String getPassword()       { return password; }
    public String getPhone()          { return phone; }
    public String getAddress()        { return address; }
    public String getRole()           { return role; }


    // Setters
    public void setUsername(String username)            { this.username = username; }
    public void setPhone(String phone)                  { this.phone = phone; }
    public void setAddress(String address)              { this.address = address; }
    public void setActiveStatus(String activeStatus)    { this.activeStatus = activeStatus; }
    public void setRole(String role)                    { this.role = role; }
}