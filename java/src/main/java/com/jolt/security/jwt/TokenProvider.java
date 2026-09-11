package com.jolt.security.jwt;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider implements InitializingBean {

    private static final Logger LOG =
            LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";

    private final String base64Secret;
    private final long tokenValidityInMilliseconds;
    private final long tokenValidityInMillisecondsForRememberMe;

    private Key key;

    public TokenProvider(
            @Value("${jwt.base64-secret}") String base64Secret,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds,
            @Value("${jwt.token-validity-in-seconds-for-remember-me}")
            long tokenValidityInSecondsForRememberMe) {

        this.base64Secret = base64Secret;
        this.tokenValidityInMilliseconds =
                tokenValidityInSeconds * 1000;
        this.tokenValidityInMillisecondsForRememberMe =
                tokenValidityInSecondsForRememberMe * 1000;
    }

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(
            Authentication authentication,
            boolean rememberMe) {

        List<String> authoritiesList = new ArrayList<>();
        Collection<? extends GrantedAuthority> authoritiesCollection =
                authentication.getAuthorities();

        for (GrantedAuthority authority : authoritiesCollection) {
            authoritiesList.add(authority.getAuthority());
        }

        String authorities = String.join(",", authoritiesList);

        long now = new Date().getTime();
        Date validity;

        if (rememberMe) {
            validity = new Date(
                    now + tokenValidityInMillisecondsForRememberMe
            );

        } else {
            validity = new Date(
                    now + tokenValidityInMilliseconds
            );
        }

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        List<GrantedAuthority> authorities = new ArrayList<>();

        String[] authorityNames =
                claims.get(AUTHORITIES_KEY).toString().split(",");

        for (String authorityName : authorityNames) {
            authorities.add(
                    new SimpleGrantedAuthority(authorityName)
            );
        }

        User principal = new User(
                claims.getSubject(),
                "",
                authorities
        );

        return new UsernamePasswordAuthenticationToken(
                principal,
                token,
                authorities
        );
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(authToken);

            return true;

        } catch (SecurityException | MalformedJwtException e) {
            LOG.info("Invalid JWT signature.");
            LOG.trace("Invalid JWT signature trace: {}", e);

        } catch (ExpiredJwtException e) {
            LOG.info("Expired JWT token.");
            LOG.trace("Expired JWT token trace: {}", e);

        } catch (UnsupportedJwtException e) {
            LOG.info("Unsupported JWT token.");
            LOG.trace("Unsupported JWT token trace: {}", e);

        } catch (IllegalArgumentException e) {
            LOG.info("JWT token is invalid.");
            LOG.trace("Invalid JWT token trace: {}", e);
        }

        return false;
    }

}
