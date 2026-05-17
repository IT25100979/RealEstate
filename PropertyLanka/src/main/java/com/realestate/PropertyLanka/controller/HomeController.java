package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.service.PropertyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private PropertyService propertyService = new PropertyService();

    @GetMapping("/")
    public String showHomePage(HttpSession session, Model model) {
        // Fetch real properties for Step 3
        model.addAttribute("allProperties", propertyService.getAllProperties());

        // Pass the user from session to the model so the Navbar can see it
        model.addAttribute("user", session.getAttribute("loggedInUser"));

        return "index";
    }
}