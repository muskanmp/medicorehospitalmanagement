package com.study.medicorehospitalmanagement.security;

import java.io.IOException;

import javax.print.attribute.standard.Media;

import org.apache.catalina.connector.Response;
import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.study.medicorehospitalmanagement.dto.LoginResponseDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor

public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final AuthService authService;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String registerId = token.getAuthorizedClientRegistrationId();

        ResponseEntity<LoginResponseDTO> entity = authService.handlerOAuth2Login(oAuth2User, registerId);

        response.setStatus(entity.getStatusCode().value());
        response.setContentType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE);
        // response.getWriter().write(objectMapper.writeValueAsString(entity.getBody()));

        response.sendRedirect("http://localhost:4200/oauth-success?token=" + entity.getBody().getJwt());
    }

}
