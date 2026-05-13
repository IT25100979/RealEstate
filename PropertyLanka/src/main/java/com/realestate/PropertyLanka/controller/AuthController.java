package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.model.User;
import com.realestate.PropertyLanka.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    // Now securely linked to your exact Service
    private UserService userService = new UserService();

    // --- 1. SHOW THE PAGES ---
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    // --- 2. HANDLE SIGN UP ---
    @PostMapping("/register")
    public String processRegistration(
            @RequestParam String username, // Changed to match your model
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String phone,    // Changed to match your model
            @RequestParam String role) {

        // Generate a unique ID
        String userId = "U" + System.currentTimeMillis();

        // Create the user using your 9-parameter constructor.
        // We supply default values for address, profilePic, and status.
        User newUser = new User(userId, username, email, password, phone, "Not Provided", role, "default.png", "Active");

        // Uses your exact method name
        userService.registerUser(newUser);

        System.out.println("✅ Registration processed for: " + username);
        return "redirect:/login";
    }

    // --- 3. HANDLE LOG IN ---
    @PostMapping("/login")
    public String processLogin(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        // Uses your exact login method
        User user = userService.login(email, password);

        if (user != null) {
            // SUCCESS! Save user to session
            session.setAttribute("loggedInUser", user);
            System.out.println("✅ Session started for: " + user.getUsername());

            // Route them based on the role defined in your class diagram
            if (user.getRole().equalsIgnoreCase("SELLER")) {
                return "redirect:/seller-dashboard";
            } else if (user.getRole().equalsIgnoreCase("ADMIN")) {
                return "redirect:/admin-dashboard";
            } else {
                return "redirect:/"; // Buyers go home
            }
        } else {
            // FAILED!
            model.addAttribute("error", "Invalid email or password!");
            return "login";
        }
    }

    // --- 4. HANDLE LOG OUT ---
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        System.out.println("👋 User Logged Out");
        return "redirect:/";
    }
}