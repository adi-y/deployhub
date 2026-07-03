package com.example.deployHub_backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private final String SECRET_KEY = "deployhubsupersecretjwtkeyforauthentication2026";
    private SecretKey getSigningKey(){
    return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
    public String generateToken(String email){
            return Jwts.builder()
                    .subject(email)
                    .issuedAt(new Date())
                    .expiration(
                            new Date(System.currentTimeMillis()+1000*60*60*24)
                    )
                    .signWith(getSigningKey())
                    .compact();
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);

    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(String token, String email){
        String username = extractUsername(token);

        return username.equals(email) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return extractClaim(
                token,
                Claims::getExpiration

        ).before(new Date());
    }
}
