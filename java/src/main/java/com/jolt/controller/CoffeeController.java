package com.jolt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/coffee")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://jolt.jennifercurtis.me"
})
public class CoffeeController {

    @Value("${yelp.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public CoffeeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String getCoffee(@RequestParam String locationId) {
        String queryUrl = UriComponentsBuilder
                .fromHttpUrl("https://api.yelp.com/v3/businesses/search")
                .queryParam("location", locationId)
                .queryParam("term", "coffee")
                .queryParam("radius", 20000)
                .queryParam("sort_by", "distance")
                .queryParam("limit", 12)
                .build()
                .encode()
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    queryUrl,
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            return response.getBody();

        } catch (HttpClientErrorException.BadRequest e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Unable to find the requested location",
                    e
            );
        }
    }

}
