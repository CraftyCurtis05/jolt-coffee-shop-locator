package com.jolt.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.security.core.AuthenticationException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class JwtAuthenticationEntryPointTests {

    private JwtAuthenticationEntryPoint sut;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private AuthenticationException authenticationException;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        sut = new JwtAuthenticationEntryPoint();
    }

    @Test
    public void commence_given_authentication_exception_sends_unauthorized_error()
            throws Exception {

        when(authenticationException.getMessage())
                .thenReturn("Authentication required");

        sut.commence(
                request,
                response,
                authenticationException
        );

        verify(response).sendError(
                HttpServletResponse.SC_UNAUTHORIZED,
                "Authentication required"
        );
    }
}
