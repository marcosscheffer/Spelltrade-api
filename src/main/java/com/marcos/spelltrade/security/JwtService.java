package com.marcos.spelltrade.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    public String generateAccessToken(UserDetails user) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                .withSubject(user.getUsername())
                .withClaim(
                    "role", 
                    user.getAuthorities()
                        .iterator()
                        .next()
                        .getAuthority()
                    )
                .withExpiresAt(Instant.now().plus(5, ChronoUnit.MINUTES))
                .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("It was not possible to generate an access token -" 
                + exception.getMessage());
        }
    }

    public String generateRefreshToken(UserDetails user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(Instant.now().plus(15, ChronoUnit.DAYS))
                .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("It was not possible to generate an access token - " 
                + exception.getMessage());
        }
    }

    public String extractSubject(String refreshToken) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        DecodedJWT jwt = 
            JWT.require(algorithm)
                .build()
                .verify(refreshToken);
        return jwt.getSubject();
    }
}
