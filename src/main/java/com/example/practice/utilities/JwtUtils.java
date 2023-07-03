package com.example.practice.utilities;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtUtils {
    @Value("${secret.key}")
    private String secretKey;

    public String generateToken(String email){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000);

        return Jwts.builder().setSubject(email).setIssuedAt(now).setExpiration(expiryDate).signWith(SignatureAlgorithm.HS256,secretKey).compact();
    }

    public String getUsernameFromToken(String token){
        Claims claim = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claim.getSubject();
    }
}
