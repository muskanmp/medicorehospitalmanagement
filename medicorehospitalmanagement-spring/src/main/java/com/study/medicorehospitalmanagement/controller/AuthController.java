package com.study.medicorehospitalmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.medicorehospitalmanagement.dto.LoginResponseDTO;
import com.study.medicorehospitalmanagement.dto.LoginResuestDTO;
import com.study.medicorehospitalmanagement.dto.SignupResponseDTO;
import com.study.medicorehospitalmanagement.security.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthController {
    
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginResuestDTO loginResuestDTO){
        return ResponseEntity.ok(authService.login(loginResuestDTO));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDTO> signup(@RequestBody LoginResuestDTO signupResuestDTO){
        return ResponseEntity.ok(authService.signup(signupResuestDTO));
    }
    
}
