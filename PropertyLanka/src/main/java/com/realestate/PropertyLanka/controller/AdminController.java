package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.model.User;
import com.realestate.PropertyLanka.service.PropertyService;
import com.realestate.PropertyLanka.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/adminportal")
    public String showAdminPortal(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null || (!user.getRole().equals("ADMIN") && !user.getRole().equals("PRIMARY_ADMIN"))) {
            return "redirect:/login";
        }

        model.addAttribute("adminUser", user);
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("allProperties", propertyService.getAllProperties());

        return "admin-dashboard";
    }
}
