package com.melihcan.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;
import java.util.Optional;

@ControllerAdvice
public class JwtTokenManager {

    private final String passwordKey = "${passwordKey}";

    private final Long exTime = 1000L*60*60;

    public Optional<String>createToken(Long id){
        String token ="";
        try {
            token = JWT.create().withAudience()
                    .withClaim("id",id)
                    .withIssuer("melihcan")
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis()+exTime))
                    .sign(Algorithm.HMAC512(passwordKey));
            return Optional.of(token);
        }catch (Exception exception){
            return Optional.empty();
        }
    }

    public Boolean validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC512(passwordKey);
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withIssuer("melihcan").build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            if (decodedJWT==null) return false;
        }catch (Exception exception){
            return false;
        }
        return true;
    }

    public Optional<Long> getIdFromToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC512(passwordKey);
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withIssuer("melihcan").build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            if (decodedJWT==null) return Optional.empty();
            return  Optional.of(decodedJWT.getClaim("id").asLong());
        }catch (Exception exception){
            return Optional.empty();
        }
    }
}
