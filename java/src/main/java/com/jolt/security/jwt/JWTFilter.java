package com.jolt.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

/**
 * Filters incoming requests and sets the Spring Security authentication
 * when a valid JWT is found.
 */
public class JWTFilter extends GenericFilterBean {

    private static final Logger LOG =
            LoggerFactory.getLogger(JWTFilter.class);

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final TokenProvider tokenProvider;

    public JWTFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest =
                (HttpServletRequest) servletRequest;

        String jwt = resolveToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication =
                    tokenProvider.getAuthentication(jwt);

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);

            LOG.debug(
                    "Set authentication in security context for '{}', uri: {}",
                    authentication.getName(),
                    requestURI
            );

        } else {
            LOG.debug("No valid JWT token found, uri: {}", requestURI);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(bearerToken) &&
                bearerToken.startsWith("Bearer ")) {

            return bearerToken.substring(7);
        }

        return null;
    }

}
