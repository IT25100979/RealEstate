package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.model.User;
import com.realestate.PropertyLanka.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserProfileController {

    @Autowired
    private UserService userService;

    // ── UPDATE PROFILE ────────────────────────────────────
    @PostMapping("/update-profile")
    public String updateProfile(
            @RequestParam String newUsername,
            @RequestParam String newPhone,
            HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        userService.updateProfile(user.getId(), newUsername, newPhone);

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

        if (user == null || !user.getId().equals(id)) {
            return "redirect:/login";
        }

        userService.deleteAccount(id);
        session.invalidate();

        System.out.println("Account deleted: " + id);
        return "redirect:/";
    }
}
