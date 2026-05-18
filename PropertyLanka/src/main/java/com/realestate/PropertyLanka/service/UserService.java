package com.realestate.PropertyLanka.service;

import com.realestate.PropertyLanka.model.User;
import com.realestate.PropertyLanka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void registerUser(User newUser) {
        if (repository.findByEmail(newUser.getEmail()).isPresent()) {
            System.out.println("❌ Registration Failed: Email already in use!");
            return;
        }
        repository.save(newUser);
        System.out.println("✅ User '" + newUser.getUsername() + "' registered.");
    }

    public User login(String email, String password) {
        return repository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .orElse(null);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserProfile(String id) {
        return repository.findById(id).orElse(null);
    }

    public void updateProfile(String id, String newUsername, String newPhone) {
        User u = getUserProfile(id);
        if (u != null) {
            u.setUsername(newUsername);
            u.setPhone(newPhone);
            repository.save(u);
        }
    }

    public void deleteAccount(String id) {
        repository.deleteById(id);
    }
    public void banUser(String id) {
        User u = repository.findById(id).orElse(null);
        if (u != null) {
            u.setActiveStatus("Banned");
            repository.save(u);
            System.out.println("🚫 User banned.");
        }
    }

    public void unbanUser(String id) {
        User u = repository.findById(id).orElse(null);
        if (u != null) {
            u.setActiveStatus("Active");
            repository.save(u);
            System.out.println("✅ User unbanned.");
        }
    }

    public void makePrimaryAdmin(String id) {
        User u = repository.findById(id).orElse(null);
        if (u != null) {
            u.setRole("PRIMARY_ADMIN");
            repository.save(u);
            System.out.println("⭐ Promoted to PRIMARY_ADMIN.");
        }
    }
}