package com.drdoom.backend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import com.drdoom.backend.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Value("${JWT_SECRET:defaultSecretKeyForTestingPurposesOnly12345678901234567890}")
    private String secretKey;

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getPhoneNumber())
                // EL CAMBIO: El toString() va dentro del paréntesis de claim
                .claim("userId", user.getId().toString())
                .claim("role", user.getRole())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey()) // Usa tu traductor de seguridad
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public java.util.UUID extractUserId(String token) {
        String idString = extractAllClaims(token)
                .get("userId", String.class);
        return java.util.UUID.fromString(idString);
    }

    public boolean isTokenExpired(String token) {
        try {
            return extractAllClaims(token).getExpiration().before(new Date());
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            return true; // Si capturamos la excepción de expiración, es que sí expiró
        }
    }


}