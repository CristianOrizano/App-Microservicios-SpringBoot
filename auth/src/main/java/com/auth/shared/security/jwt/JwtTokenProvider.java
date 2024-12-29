package com.auth.shared.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    public String generarToken(Authentication authentication) {
        String username = authentication.getName(); //obtnemos el nombre usuario
        Date fechaActual = new Date(); //obtenemos fecha emision(actual)
        Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpirationInMs);//sumamos los ms al tiempo actual ms
        SecretKey key = getSecretKey();

        String token = Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(fechaExpiracion)
                .signWith(key).compact();

        return token;
    }
    public JWTAuthResonseDTO getSecurity(Authentication authentication) {
        JWTAuthResonseDTO security = new JWTAuthResonseDTO();

        String token = generarToken(authentication);
        security.setTokenDeAcceso(token);
        security.setExpiresOn(getExpirationDateFromToken(token));

        return security;
    }

    public Claims getClaimsFromToken(String token) {
        SecretKey key = getSecretKey();

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUserNameFromToken(String token){
        return getClaimsFromToken(token).getSubject();
    }
    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    public boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = getUserNameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(this.jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
}
