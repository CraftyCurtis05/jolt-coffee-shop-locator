package com.jolt.controller;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.ArgumentCaptor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

public class CoffeeControllerTests {

    private CoffeeController sut;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        sut = new CoffeeController(restTemplate);
    }

    @Test
    public void getCoffee_given_valid_location_returns_yelp_response() {
        String yelpResponse =
                "{\"businesses\":[{\"name\":\"Test Coffee\"}]}";

        when(restTemplate.exchange(
                any(String.class),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class)
        )).thenReturn(
                ResponseEntity.ok(yelpResponse)
        );

        String response =
                sut.getCoffee("Columbus, OH");

        Assert.assertEquals(
                yelpResponse,
                response
        );
    }

    @Test
    public void getCoffee_given_yelp_bad_request_throws_bad_request() {
        when(restTemplate.exchange(
                any(String.class),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class)
        )).thenThrow(
            HttpClientErrorException.create(
                    HttpStatus.BAD_REQUEST,
                    "Bad Request",
                    null,
                    null,
                    null
            )
        );

        try {
            sut.getCoffee("Invalid Location");
            Assert.fail("Expected ResponseStatusException");

        } catch (ResponseStatusException e) {
            Assert.assertEquals(
                    400,
                    e.getStatus().value()
            );
        }
    }

    @Test
    public void getCoffee_given_location_builds_expected_yelp_request() {
        when(restTemplate.exchange(
                any(String.class),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class)
        )).thenReturn(
                ResponseEntity.ok("{}")
        );

        sut.getCoffee("Columbus, OH");

        ArgumentCaptor<String> urlCaptor =
                ArgumentCaptor.forClass(String.class);

        verify(restTemplate).exchange(
                urlCaptor.capture(),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class)
        );

        String requestUrl = urlCaptor.getValue();

        Assert.assertTrue(
                requestUrl.contains("location=Columbus,%20OH")
        );

        Assert.assertTrue(
                requestUrl.contains("term=coffee")
        );

        Assert.assertTrue(
                requestUrl.contains("radius=15000")
        );

        Assert.assertTrue(
                requestUrl.contains("sort_by=distance")
        );

        Assert.assertTrue(
                requestUrl.contains("limit=12")
        );
    }
}
