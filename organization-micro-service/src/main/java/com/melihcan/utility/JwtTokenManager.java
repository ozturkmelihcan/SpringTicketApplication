package com.melihcan.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.melihcan.exception.EErrorType;
import com.melihcan.exception.OrganizationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;
import java.util.Optional;

@ControllerAdvice
public class JwtTokenManager {

    @Value("${jwt.secretKey}")
    private String passwordKey;

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

    public Optional<Long> getIdFromToken(String token){

            Algorithm algorithm = Algorithm.HMAC512(passwordKey);
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withIssuer("melihcan").build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            if (decodedJWT==null) throw new OrganizationException(EErrorType.INVALID_TOKEN);
            return  Optional.of(decodedJWT.getClaim("id").asLong());

    }
}
