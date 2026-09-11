package com.jolt.security;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

    private static final Logger LOG =
            LoggerFactory.getLogger(SecurityUtils.class);

    private SecurityUtils() {
    }

    public static Optional<String> getCurrentUsername() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            LOG.debug("No authentication found in security context");
            return Optional.empty();
        }

        String username = null;

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser =
                    (UserDetails) authentication.getPrincipal();

            username = springSecurityUser.getUsername();

        } else if (authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        }

        LOG.debug("Found username '{}' in security context", username);

        return Optional.ofNullable(username);
    }

}
