package com.ti.Skanboo.security;

import java.util.Date;
import java.util.Objects;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {

    @Value("${jwt.secret}") // pega o valor da application.proprieties
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String email) {
        SecretKey key = getKeyBySecret();
        return Jwts.builder().setSubject(email).setExpiration(new Date(System.currentTimeMillis() + this.expiration))
                .signWith(key).compact();

                //conferir se o setSubject pode ser 'email'
    }

    // Key para geracao do Token
    private SecretKey getKeyBySecret() {
        return Keys.hmacShaKeyFor(this.secret.getBytes());
    }

    public boolean isValidToken(String token) {
        Claims claims = getClaims(token);
        if (Objects.nonNull(claims)) {
            String email = claims.getSubject();
            Date experationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());

            if (Objects.nonNull(email) && Objects.nonNull(experationDate) && now.before(experationDate))
                return true;
        }
        return false;
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        if (Objects.nonNull(claims))
            return claims.getSubject();
        return null;
    }

    private Claims getClaims(String token) {
        SecretKey key = getKeyBySecret();

        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }
}