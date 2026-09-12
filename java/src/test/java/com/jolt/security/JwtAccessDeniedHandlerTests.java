package com.jolt.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.security.access.AccessDeniedException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class JwtAccessDeniedHandlerTests {

    private JwtAccessDeniedHandler sut;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private AccessDeniedException accessDeniedException;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        sut = new JwtAccessDeniedHandler();
    }

    @Test
    public void handle_given_access_denied_exception_sends_forbidden_error()
            throws Exception {

        when(accessDeniedException.getMessage())
                .thenReturn("Access denied");

        sut.handle(
                request,
                response,
                accessDeniedException
        );

        verify(response).sendError(
                HttpServletResponse.SC_FORBIDDEN,
                "Access denied"
        );
    }
}
