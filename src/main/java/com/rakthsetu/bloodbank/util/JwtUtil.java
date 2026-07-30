package com.rakthsetu.bloodbank.util;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Token generate karna — email aur role dono payload me daalenge
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)                                  // "subject" = kiske liye token hai
                .claim("role", role)                                 // Extra custom data
                .setIssuedAt(new Date())                             // Kab bana
                .setExpiration(new Date(System.currentTimeMillis() + expiration))  // Kab expire hoga
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)  // Secret key se sign karo
                .compact();
    }

    // Token se email nikalna
    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Token se role nikalna
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // Token expire ho gaya ya nahi check karna
    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // Token valid hai ya nahi (signature + expiry dono check)
    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;   // Tampered ya malformed token
        }
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
