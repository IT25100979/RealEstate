package com.realestate.PropertyLanka.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * PageController — Sachindu (Member 3)
 * Serves all HTML pages from src/main/resources/templates/
 *
 * Package FIXED: com.realestate.PropertyLanka.controller
 * (was wrongly set to com.propertyapp.controller)
 */
@RestController
public class PageController {

    private ResponseEntity<String> serveHtml(String filename) {
        try {
            Resource resource = new ClassPathResource("templates/" + filename);
            String content = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_HTML)
                    .body(content);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<String> index() { return serveHtml("index.html"); }

    @GetMapping("/index")
    public ResponseEntity<String> indexAlt() { return serveHtml("index.html"); }

    @GetMapping("/properties")
    public ResponseEntity<String> properties() { return serveHtml("properties.html"); }

    @GetMapping("/property")
    public ResponseEntity<String> propertyDetail() { return serveHtml("property-detail.html"); }

    @GetMapping("/search")
    public ResponseEntity<String> search() { return serveHtml("search.html"); }

    @GetMapping("/add-property")
    public ResponseEntity<String> addProperty() { return serveHtml("add-property.html"); }

    @GetMapping("/my-listings")
    public ResponseEntity<String> myListings() { return serveHtml("my-listings.html"); }

    @GetMapping("/admin")
    public ResponseEntity<String> admin() { return serveHtml("admin.html"); }

    @GetMapping("/register")
    public ResponseEntity<String> register() { return serveHtml("register.html"); }
}
