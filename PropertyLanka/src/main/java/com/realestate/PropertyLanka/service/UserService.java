package com.realestate.PropertyLanka.service;

import com.realestate.PropertyLanka.model.User;
import com.realestate.PropertyLanka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}