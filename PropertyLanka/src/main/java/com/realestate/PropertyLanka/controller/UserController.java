package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.model.User;
import com.realestate.PropertyLanka.service.UserService;

public class UserController {
    private UserService service;

    public UserController() {
        this.service = new UserService();
    }

    public void register(String id, String username, String email, String password, String phone, String address, String role, String pic, String status) {
        System.out.println("\n--- API: POST /users/register ---");
        User newUser = new User(id, username, email, password, phone, address, role, pic, status);
        service.registerUser(newUser);
    }

    public void login(String email, String password) {
        System.out.println("\n--- API: POST /users/login ---");
        User loggedInUser = service.login(email, password);
        if (loggedInUser != null) {
            System.out.println("Welcome to the dashboard, " + loggedInUser.getRole() + "!");
        }
    }

    public void viewProfile(String id) {
        System.out.println("\n--- API: GET /users/" + id + " ---");
        User u = service.getUserProfile(id);
        if (u != null) {
            System.out.println(u.toString());
        }
    }

    public void editProfile(String id, String newUsername, String newPhone) {
        System.out.println("\n--- API: PUT /users/" + id + " ---");
        service.updateProfile(id, newUsername, newPhone);
    }

    public void deleteAccount(String id) {
        System.out.println("\n--- API: DELETE /users/" + id + " ---");
        service.deleteAccount(id);
    }
}
