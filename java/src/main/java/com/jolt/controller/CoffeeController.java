package com.jolt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/coffee")
@CrossOrigin(origins = {"http://localhost:5173", "https://jolt.jennifercurtis.me"})

public class CoffeeController {

    @Value("${yelp.api.key}")  // Store API key in application.properties
    private String apiKey;

    private final RestTemplate restTemplate;

    public CoffeeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping()
    public String getCoffee(@RequestParam String locationId) {
        // Construct the URL for the external API request
        String queryURL = UriComponentsBuilder
                .fromHttpUrl("https://api.yelp.com/v3/businesses/search")
                .queryParam("location", locationId)
                .queryParam("term", "coffee tea")
                .queryParam("radius", 20000)
                .queryParam("sort_by", "distance")
                .queryParam("limit", 20)
                .build()
                .encode()
                .toUriString();

        // Prepare the request headers with the API key
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);  // Add API key securely

        // Create a new HttpEntity with the headers
        org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(headers);

        // Use RestTemplate to send the GET request to the external API and get the response
        ResponseEntity<String> response = restTemplate.exchange(
                queryURL, HttpMethod.GET, entity, String.class);

        // Log the full response for debugging
        // System.out.println("Yelp Response: " + response.getBody());

        // Return the response body (which contains the actual data)
        return response.getBody();
    }
}