package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.model.User;
import com.realestate.PropertyLanka.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * UserProfileController
 *
 * Handles:
 *   POST /update-profile     → save username + phone changes
 *   GET  /delete-account/{id} → delete account and logout
 *
 * Added by Member 3 (Sachindu)
 */
@Controller
public class UserProfileController {

    private UserService userService = new UserService();

    // ── UPDATE PROFILE ────────────────────────────────────
    @PostMapping("/update-profile")
    public String updateProfile(
            @RequestParam String newUsername,
            @RequestParam String newPhone,
            HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        // Update via service
        userService.updateProfile(user.getId(), newUsername, newPhone);

        // Update session so nav shows new name immediately
        user.setUsername(newUsername);
        user.setPhone(newPhone);
        session.setAttribute("loggedInUser", user);

        System.out.println("Profile updated for: " + user.getId());
        return "redirect:/profile";
    }

    // ── DELETE ACCOUNT ────────────────────────────────────
    @GetMapping("/delete-account/{id}")
    public String deleteAccount(@PathVariable String id, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");

        // Security: can only delete your OWN account
        if (user == null || !user.getId().equals(id)) {
            return "redirect:/login";
        }

        userService.deleteAccount(id);
        session.invalidate();

        System.out.println("Account deleted: " + id);
        return "redirect:/";
    }
}
