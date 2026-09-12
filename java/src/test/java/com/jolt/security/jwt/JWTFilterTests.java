package com.jolt.security.jwt;

import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTFilterTests {

    private JWTFilter sut;

    @Mock
    private TokenProvider tokenProvider;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private Authentication authentication;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        SecurityContextHolder.clearContext();

        sut = new JWTFilter(tokenProvider);
    }

    @After
    public void cleanup() {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void doFilter_given_valid_bearer_token_sets_authentication() throws Exception {
        when(request.getHeader(JWTFilter.AUTHORIZATION_HEADER))
                .thenReturn("Bearer valid-token");

        when(request.getRequestURI())
                .thenReturn("/profile");

        when(tokenProvider.validateToken("valid-token"))
                .thenReturn(true);

        when(tokenProvider.getAuthentication("valid-token"))
                .thenReturn(authentication);

        sut.doFilter(
                request,
                response,
                filterChain
        );

        Assert.assertSame(
                authentication,
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
        );

        verify(filterChain).doFilter(
                request,
                response
        );
    }

    @Test
    public void doFilter_given_no_authorization_header_does_not_set_authentication()
            throws Exception {

        when(request.getHeader(JWTFilter.AUTHORIZATION_HEADER))
                .thenReturn(null);

        when(request.getRequestURI())
                .thenReturn("/profile");

        sut.doFilter(
                request,
                response,
                filterChain
        );

        Assert.assertNull(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
        );

        verify(filterChain).doFilter(
                request,
                response
        );
    }

    @Test
    public void doFilter_given_invalid_bearer_token_does_not_set_authentication()
            throws Exception {

        when(request.getHeader(JWTFilter.AUTHORIZATION_HEADER))
                .thenReturn("Bearer invalid-token");

        when(request.getRequestURI())
                .thenReturn("/profile");

        when(tokenProvider.validateToken("invalid-token"))
                .thenReturn(false);

        sut.doFilter(
                request,
                response,
                filterChain
        );

        Assert.assertNull(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
        );

        verify(filterChain).doFilter(
                request,
                response
        );
    }

    @Test
    public void doFilter_given_non_bearer_authorization_header_does_not_set_authentication()
            throws Exception {

        when(request.getHeader(JWTFilter.AUTHORIZATION_HEADER))
                .thenReturn("Basic abc123");

        when(request.getRequestURI())
                .thenReturn("/profile");

        sut.doFilter(
                request,
                response,
                filterChain
        );

        Assert.assertNull(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
        );

        verify(filterChain).doFilter(
                request,
                response
        );
    }
}
