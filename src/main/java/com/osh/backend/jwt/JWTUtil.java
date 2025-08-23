package com.osh.backend.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * 메소드 종류
 * 1. 토큰 생성
 * 2. username 추출
 * 3. role 추출
 * 4. 토큰 만료여부 확인
 */
@Slf4j
@Component
public class JWTUtil {

    private static final Long EXPIRED_MS = 60 * 60 * 100L;

    private final SecretKey secretKey;

    public JWTUtil(@Value("${spring.jwt.secret}") String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * JWT 토큰 생성
     * @param username 이메일
     * @param role 권한
     * @param expiredMs 유효기간
     * @return 생성된 토큰 값
     */
    public String generateJwt(String username, String role) {
        return Jwts.builder()
                .claim("username", username)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRED_MS))
                .signWith(secretKey)
                .compact();
    }

    /**
     * JWT 토큰에서 username(Email) 추출
     * @param token JWT 토큰
     * @return username(Email)
     */
    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("username", String.class);
    }

    /**
     * JWT 토큰에서 ROLE 추출
     * @param token JWT 토큰
     * @return role(권한)
     */
    public String getRole(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }

    /**
     * 토큰 유효기간 만료 여부 확인
     * @param token JWT 토큰
     * @return true - 토큰 만료 / false - 토큰 만료 안됨
     */
    public boolean isExpired(String token) {
        try{
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return false;
        } catch (Exception e) {
            log.error("JWT : {}", e.getMessage());
        }
        return true;
    }
}
