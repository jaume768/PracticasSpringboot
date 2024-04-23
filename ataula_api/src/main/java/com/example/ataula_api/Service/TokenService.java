package com.example.ataula_api.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Service
public class TokenService {

    private static final long EXPIRATION_TIME = 240_000; // 4 minutos
    private SecretKey SECRET_KEY;

    @Value("${jwt.secret}")
    private String base64Secret;

    @PostConstruct
    public void init() {
        byte[] decodedKey = Base64.getDecoder().decode(base64Secret.getBytes(StandardCharsets.UTF_8));
        SECRET_KEY = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA512");
    }

    public String generarToken(String telefono,String nombre,int id) {
        return Jwts.builder()
                .setSubject(telefono)
                .claim("id", id)
                .claim("telefono", telefono)
                .claim("nombre", nombre)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();
    }

    public String extractEmailFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("email", String.class);
    }
}
