package com.realestate.PropertyLanka.controller;

import com.realestate.PropertyLanka.model.Property;
import com.realestate.PropertyLanka.service.SearchService;
import com.realestate.PropertyLanka.util.PriceAscComparator;
import com.realestate.PropertyLanka.util.PriceDescComparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class SearchController {

    private SearchService searchService = new SearchService();

    @GetMapping("/search")
    public String showSearchPage(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String propertyType,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "newest") String sort,
            Model model) {

        // Echo filter values back so form stays filled in
        model.addAttribute("keyword",      keyword);
        model.addAttribute("address",      address);
        model.addAttribute("propertyType", propertyType);
        model.addAttribute("status",       status);
        model.addAttribute("maxPrice",     maxPrice);
        model.addAttribute("sort",         sort);

        // Remove the early return so that all properties are shown by default

        // 1. Filter using the new SearchService
        List<Property> results = searchService.searchProperties(keyword, address, propertyType, status, maxPrice);

        // 2. Sort using OOP Comparators
        if ("price_asc".equals(sort)) {
            results.sort(new PriceAscComparator());
        } else if ("price_desc".equals(sort)) {
            results.sort(new PriceDescComparator());
        } else {
            // "newest"
            Collections.reverse(results);
        }

        model.addAttribute("results",     results);
        model.addAttribute("resultCount", results.size());

        return "search";
    }
}
