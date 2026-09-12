package com.jolt.security;

import com.jolt.JoltApplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JoltApplication.class)
@AutoConfigureMockMvc
public class WebSecurityIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
    }

    @Test
    public void coffeeEndpoint_given_no_authentication_returns_unauthorized()
            throws Exception {

        mockMvc.perform(
                get("/coffee")
                        .param("locationId", "Columbus, OH")
        )
                .andExpect(
                        status().isUnauthorized()
                );
    }

    @Test
    public void favoritesEndpoint_given_no_authentication_returns_unauthorized()
            throws Exception {

        mockMvc.perform(
                get("/favorites")
        )
                .andExpect(
                        status().isUnauthorized()
                );
    }

    @Test
    public void profileEndpoint_given_no_authentication_returns_unauthorized()
            throws Exception {

        mockMvc.perform(
                get("/profile")
        )
                .andExpect(
                        status().isUnauthorized()
                );
    }

    @Test
    public void imageEndpoint_given_no_authentication_returns_unauthorized()
            throws Exception {

        mockMvc.perform(
                get("/image")
        )
                .andExpect(
                        status().isUnauthorized()
                );
    }
}
