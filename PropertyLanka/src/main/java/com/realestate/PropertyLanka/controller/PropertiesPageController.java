package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PropertiesPageController {

    @Autowired
    private PropertyService propertyService;


    @GetMapping("/properties")
    public String showPropertiesPage(Model model) {
        model.addAttribute("allProperties", propertyService.getAllProperties());
        return "properties";
    }
}
