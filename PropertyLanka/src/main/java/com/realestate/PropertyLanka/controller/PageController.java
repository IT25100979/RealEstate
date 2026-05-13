package com.propertyapp.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;

/**
 * PageController — Member 3 (Sachindu)
 *
 * Serves all HTML pages from src/main/resources/templates/
 * Each @GetMapping returns the corresponding HTML file.
 *
 * File structure:
 *   src/main/resources/
 *     templates/          ← HTML files served here
 *       index.html
 *       search.html
 *       properties.html
 *       property-detail.html
 *       add-property.html
 *       login.html
 *       register.html
 *       my-listings.html
 *       admin.html
 *     static/
 *       css/
 *         style.css       ← served at /css/style.css
 *       js/
 *         theme-toggle.js ← served at /js/theme-toggle.js
 *         particles.js    ← served at /js/particles.js
 *         search.js       ← served at /js/search.js
 *         admin.js        ← served at /js/admin.js
 */
@RestController
public class PageController {

    // ── Helper — reads HTML file from templates folder ────
    private ResponseEntity<String> serveHtml(String filename) {
        try {
            Resource resource = new ClassPathResource("templates/" + filename);
            String content = new String(Files.readAllBytes(resource.getFile().toPath()));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_HTML)
                    .body(content);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ── Page routes ───────────────────────────────────────

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return serveHtml("index.html");
    }

    @GetMapping("/index")
    public ResponseEntity<String> indexAlt() {
        return serveHtml("index.html");
    }

    @GetMapping("/properties")
    public ResponseEntity<String> properties() {
        return serveHtml("properties.html");
    }

    @GetMapping("/property")
    public ResponseEntity<String> propertyDetail() {
        return serveHtml("property-detail.html");
    }

    @GetMapping("/search")
    public ResponseEntity<String> search() {
        return serveHtml("search.html");
    }

    @GetMapping("/add-property")
    public ResponseEntity<String> addProperty() {
        return serveHtml("add-property.html");
    }

    @GetMapping("/my-listings")
    public ResponseEntity<String> myListings() {
        return serveHtml("my-listings.html");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return serveHtml("admin.html");
    }

    @GetMapping("/login")
    public ResponseEntity<String> login() {
        return serveHtml("login.html");
    }

    @GetMapping("/register")
    public ResponseEntity<String> register() {
        return serveHtml("register.html");
    }
}
