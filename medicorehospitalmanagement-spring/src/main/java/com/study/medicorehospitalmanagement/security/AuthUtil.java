package com.study.medicorehospitalmanagement.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties.Apiversion.Use;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.study.medicorehospitalmanagement.entities.User;
import com.study.medicorehospitalmanagement.entities.type.AuthproviderType;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class AuthUtil {
    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user){
        return Jwts.builder()
        .subject(user.getUsername())
        .claim("usedid", user.getId().toString())
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
        .signWith(getSecretKey())
        .compact();
        
    }

    public String getUsernameFromToken(String token) {
        System.out.println("Token received: " + token);
        Claims claims = Jwts.parser()
        .verifyWith(getSecretKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
        return claims.getSubject();
    }

    public AuthproviderType getAuthproviderType(String reString){
        return switch (reString.toLowerCase()) {
            case "google" -> AuthproviderType.GOOGLE;
            case "github" -> AuthproviderType.GITHUB;
            case "twitter" -> AuthproviderType.TWITTER;
            default -> throw new IllegalArgumentException("Unknown OAuth provider type: " + reString);
        };
    }

    public String determineProviderIdFromOAuth2(String registerId, OAuth2User oAuth2User) {
        String providerId =switch (registerId.toLowerCase()) {         
            case "google" -> oAuth2User.getAttribute("sub");
            case "github" -> oAuth2User.getAttribute("id").toString();
            default -> throw new IllegalArgumentException("Unknown OAuth provider type: " + registerId);
        };

        if(providerId == null || providerId.isEmpty()){
            throw new IllegalArgumentException("Provider ID not found in OAuth2User attributes for provider: " + registerId);
        }

        return providerId;
    }

    public String determineUsernameFromOAuth2(OAuth2User auth2User, String regId, String provdId){
        String email = auth2User.getAttribute("email");
        if (email !=null && !email.isBlank()){
            return email;
        }
        return switch (regId.toLowerCase()){
            case "google" -> auth2User.getAttribute("sub");
            case "github" -> auth2User.getAttribute("login");
            default -> provdId;
        };
    }

}
