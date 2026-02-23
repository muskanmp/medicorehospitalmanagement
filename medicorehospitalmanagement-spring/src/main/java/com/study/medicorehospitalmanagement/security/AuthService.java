package com.study.medicorehospitalmanagement.security;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.medicorehospitalmanagement.dto.LoginResponseDTO;
import com.study.medicorehospitalmanagement.dto.LoginResuestDTO;
import com.study.medicorehospitalmanagement.dto.SignupResponseDTO;
import com.study.medicorehospitalmanagement.entities.User;
import com.study.medicorehospitalmanagement.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class AuthService {
    
    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDTO login(LoginResuestDTO loginResuestDTO) {


        Authentication authentication = authenticationManager.authenticate(
             new UsernamePasswordAuthenticationToken(loginResuestDTO.getUsername(), loginResuestDTO.getPassword())
        );
        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccess(user);

        return new LoginResponseDTO(token, user.getId());
    }

    public SignupResponseDTO signup(LoginResuestDTO signupResuestDTO) {

        User user = userRepository.findByUsername(signupResuestDTO.getUsername()).orElse(null);
        if(user != null) throw new RuntimeException("User already exists");

        user = userRepository.save(User.builder()
        .username(signupResuestDTO.getUsername())
        .password(passwordEncoder.encode(signupResuestDTO.getPassword()))
        .build());
        return new SignupResponseDTO(user.getId(), user.getUsername());
    }
    
}
