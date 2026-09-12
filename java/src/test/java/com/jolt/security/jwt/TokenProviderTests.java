package com.jolt.security.jwt;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class TokenProviderTests {

    private TokenProvider sut;

    @Before
    public void setup() {
        String secret = Base64.getEncoder().encodeToString(
                "this-is-a-test-secret-long-enough-for-hs512-signing-key-1234567890"
                        .getBytes(StandardCharsets.UTF_8)
        );

        sut = new TokenProvider(
                secret,
                3600,
                7200
        );

        sut.afterPropertiesSet();
    }

    @Test
    public void createToken_given_authentication_returns_valid_token() {
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        "testuser",
                        "password",
                        Collections.singletonList(
                                new SimpleGrantedAuthority("ROLE_USER")
                        )
                );

        String token =
                sut.createToken(authentication, false);

        Assert.assertNotNull(token);

        Assert.assertTrue(
                sut.validateToken(token)
        );
    }

    @Test
    public void getAuthentication_given_valid_token_returns_expected_authentication() {
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        "testuser",
                        "password",
                        Collections.singletonList(
                                new SimpleGrantedAuthority("ROLE_USER")
                        )
                );

        String token =
                sut.createToken(authentication, false);

        Authentication result =
                sut.getAuthentication(token);

        Assert.assertEquals(
                "testuser",
                result.getName()
        );

        Assert.assertTrue(
                result.getAuthorities().contains(
                        new SimpleGrantedAuthority("ROLE_USER")
                )
        );

        Assert.assertEquals(
                token,
                result.getCredentials()
        );
    }

    @Test
    public void validateToken_given_malformed_token_returns_false() {
        boolean result =
                sut.validateToken("not-a-valid-jwt");

        Assert.assertFalse(result);
    }

    @Test
    public void validateToken_given_expired_token_returns_false() {
        String secret = Base64.getEncoder().encodeToString(
                "this-is-a-test-secret-long-enough-for-hs512-signing-key-1234567890"
                        .getBytes(StandardCharsets.UTF_8)
        );

        TokenProvider expiredTokenProvider =
                new TokenProvider(
                        secret,
                        -1,
                        -1
                );

        expiredTokenProvider.afterPropertiesSet();

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        "testuser",
                        "password",
                        Collections.singletonList(
                                new SimpleGrantedAuthority("ROLE_USER")
                        )
                );

        String token =
                expiredTokenProvider.createToken(
                        authentication,
                        false
                );

        Assert.assertFalse(
                expiredTokenProvider.validateToken(token)
        );
    }

    @Test
    public void createToken_given_rememberMe_uses_longer_expiration() {
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        "testuser",
                        "password",
                        Collections.singletonList(
                                new SimpleGrantedAuthority("ROLE_USER")
                        )
                );

        String normalToken =
                sut.createToken(authentication, false);

        String rememberMeToken =
                sut.createToken(authentication, true);

        Claims normalClaims = Jwts.parserBuilder()
                .setSigningKey(
                        Keys.hmacShaKeyFor(
                                Decoders.BASE64.decode(
                                        Base64.getEncoder().encodeToString(
                                                "this-is-a-test-secret-long-enough-for-hs512-signing-key-1234567890"
                                                        .getBytes(StandardCharsets.UTF_8)
                                        )
                                )
                        )
                )
                .build()
                .parseClaimsJws(normalToken)
                .getBody();

        Claims rememberMeClaims = Jwts.parserBuilder()
                .setSigningKey(
                        Keys.hmacShaKeyFor(
                                Decoders.BASE64.decode(
                                        Base64.getEncoder().encodeToString(
                                                "this-is-a-test-secret-long-enough-for-hs512-signing-key-1234567890"
                                                        .getBytes(StandardCharsets.UTF_8)
                                        )
                                )
                        )
                )
                .build()
                .parseClaimsJws(rememberMeToken)
                .getBody();

        Assert.assertTrue(
                rememberMeClaims.getExpiration().after(
                        normalClaims.getExpiration()
                )
        );
    }
}
