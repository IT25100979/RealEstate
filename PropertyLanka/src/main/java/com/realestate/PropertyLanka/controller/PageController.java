package com.realestate.PropertyLanka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/index")
    public String indexAlt() { return "index"; }

    @GetMapping("/property")
    public String propertyDetail() { return "property-detail"; }

    @GetMapping("/my-listings")
    public String myListings() { return "my-listings"; }

    @GetMapping("/admin")
    public String admin() { return "admin"; }

}
