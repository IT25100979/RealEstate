package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.model.User;
import com.realestate.PropertyLanka.service.InquiryService;
import com.realestate.PropertyLanka.service.PropertyService;
import com.realestate.PropertyLanka.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminController {

    @Autowired private UserService userService;
    @Autowired private PropertyService propertyService;
    @Autowired private InquiryService inquiryService;

    //  Show admin dashboard
    @GetMapping("/adminportal")
    public String showAdminPortal(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || (!user.getRole().equals("ADMIN") && !user.getRole().equals("PRIMARY_ADMIN"))) {
            return "redirect:/login";
        }
        model.addAttribute("adminUser", user);
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("allProperties", propertyService.getAllProperties());
        model.addAttribute("allInquiries", inquiryService.getAllInquiries());
        return "admin-dashboard";
    }

    // Ban a user
    @GetMapping("/admin/ban/{id}")
    public String banUser(@PathVariable String id, HttpSession session) {
        User admin = (User) session.getAttribute("loggedInUser");
        if (admin == null || (!admin.getRole().equals("ADMIN") && !admin.getRole().equals("PRIMARY_ADMIN"))) {
            return "redirect:/login";
        }
        userService.banUser(id);
        return "redirect:/adminportal";
    }

    // Unban a user
    @GetMapping("/admin/unban/{id}")
    public String unbanUser(@PathVariable String id, HttpSession session) {
        User admin = (User) session.getAttribute("loggedInUser");
        if (admin == null || (!admin.getRole().equals("ADMIN") && !admin.getRole().equals("PRIMARY_ADMIN"))) {
            return "redirect:/login";
        }
        userService.unbanUser(id);
        return "redirect:/adminportal";
    }

    // Delete a user
    @GetMapping("/admin/delete-user/{id}")
    public String deleteUser(@PathVariable String id, HttpSession session) {
        User admin = (User) session.getAttribute("loggedInUser");
        if (admin == null || (!admin.getRole().equals("ADMIN") && !admin.getRole().equals("PRIMARY_ADMIN"))) {
            return "redirect:/login";
        }
        userService.deleteAccount(id);
        return "redirect:/adminportal";
    }

    // Make Primary Admin
    @GetMapping("/admin/make-primary/{id}")
    public String makePrimary(@PathVariable String id, HttpSession session) {
        User admin = (User) session.getAttribute("loggedInUser");
        if (admin == null || !admin.getRole().equals("PRIMARY_ADMIN")) {
            return "redirect:/login";
        }
        userService.makePrimaryAdmin(id);
        return "redirect:/adminportal";
    }

    // Force delete a property
    @GetMapping("/admin/delete-property/{id}")
    public String deleteProperty(@PathVariable String id, HttpSession session) {
        User admin = (User) session.getAttribute("loggedInUser");
        if (admin == null || (!admin.getRole().equals("ADMIN") && !admin.getRole().equals("PRIMARY_ADMIN"))) {
            return "redirect:/login";
        }
        propertyService.removeProperty(id);
        return "redirect:/adminportal";
    }

    // Delete an inquiry
    @GetMapping("/admin/delete-inquiry/{id}")
    public String deleteInquiry(@PathVariable String id, HttpSession session) {
        User admin = (User) session.getAttribute("loggedInUser");
        if (admin == null || (!admin.getRole().equals("ADMIN") && !admin.getRole().equals("PRIMARY_ADMIN"))) {
            return "redirect:/login";
        }
        inquiryService.deleteInquiry(id);
        return "redirect:/adminportal";
    }
}