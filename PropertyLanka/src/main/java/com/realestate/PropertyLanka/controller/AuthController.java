package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.model.User;
import com.realestate.PropertyLanka.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    //  SHOW THE PAGES
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    //  HANDLE SIGN UP
    @PostMapping("/register")
    public String processRegistration(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String phone,
            @RequestParam String role) {

        String userId = "U" + System.currentTimeMillis();
        User newUser = new User(userId, username, email, password, phone, "Not Provided", role, "default.png", "Active");
        userService.registerUser(newUser);

        System.out.println(" Registration processed for: " + username);
        return "redirect:/login";
    }

    // HANDLE LOG IN
    @PostMapping("/login")
    public String processLogin(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        User user = userService.login(email, password);

        if (user != null) {
            session.setAttribute("loggedInUser", user);
            System.out.println(" Session started for: " + user.getUsername());

            if (user.getRole().equalsIgnoreCase("SELLER")) {
                return "redirect:/seller-dashboard";
            } else if (user.getRole().equalsIgnoreCase("ADMIN") || user.getRole().equalsIgnoreCase("PRIMARY_ADMIN")) {
                return "redirect:/adminportal";
            } else {
                return "redirect:/";
            }
        } else {
            model.addAttribute("error", "Invalid email or password!");
            return "login";
        }
    }

    // HANDLE LOG OUT
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        System.out.println(" User Logged Out");
        return "redirect:/";
    }
}
