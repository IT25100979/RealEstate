package com.realestate.PropertyLanka.service;

import com.realestate.PropertyLanka.model.User;
import com.realestate.PropertyLanka.repository.UserRepository;
import java.util.List;

public class UserService {

    private UserRepository repository;

    public UserService() {
        this.repository = new UserRepository();
    }

    // ── REGISTER ─────────────────────────────────────────
    public void registerUser(User newUser) {
        for (User u : repository.findAll()) {
            if (u.getEmail().equalsIgnoreCase(newUser.getEmail())) {
                System.out.println("❌ Registration Failed: Email already in use!");
                return;
            }
        }
        repository.save(newUser);
        System.out.println("✅ Service: User '" + newUser.getUsername() + "' registered successfully.");
    }

    // ── LOGIN ────────────────────────────────────────────
    public User login(String email, String password) {
        for (User u : repository.findAll()) {
            if (u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password)) {
                System.out.println("✅ Service: Login successful for " + u.getUsername());
                return u;
            }
        }
        System.out.println("❌ Login Failed: Incorrect email or password.");
        return null;
    }

    // ── GET ONE USER ─────────────────────────────────────
    public User getUserProfile(String id) {
        for (User u : repository.findAll()) {
            if (u.getId().equals(id)) return u;
        }
        System.out.println("❌ Error: User not found.");
        return null;
    }

    // ── GET ALL USERS ────────────────────────────────────
    // ← ADDED: used by AdminController
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    // ── UPDATE PROFILE ───────────────────────────────────
    public void updateProfile(String id, String newUsername, String newPhone) {
        User u = getUserProfile(id);
        if (u != null) {
            u.setUsername(newUsername);
            u.setPhone(newPhone);
            repository.update(u);
            System.out.println("✅ Service: Profile updated successfully.");
        }
    }

    // ── DELETE ACCOUNT ───────────────────────────────────
    public void deleteAccount(String id) {
        if (getUserProfile(id) != null) {
            repository.delete(id);
            System.out.println("✅ Service: Account deleted successfully.");
        }
    }
}
