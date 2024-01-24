package med.clinica.spring.api.infra.security.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import med.clinica.spring.api.domain.user.repository.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${jwt.secret}")
    private String secret;
    public String generateToken(UserEntity user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("CLINICA")
                    .withSubject(user.getUsername())
                    .withExpiresAt(this.generateExpireDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("JWT error generate", exception);
        }
    }

    public boolean isValidToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decoded = JWT.require(algorithm)
                    .withIssuer("CLINICA")
                    .build()
                    .verify(token);
            return true;
        }catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid token has been sended", exception);
        }
    }
    public Instant generateExpireDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-04:00"));
    }
}
