package com.study.medicorehospitalmanagement.security;

import java.util.Date;
import java.util.Set;

import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.medicorehospitalmanagement.dto.LoginResponseDTO;
import com.study.medicorehospitalmanagement.dto.LoginResuestDTO;
import com.study.medicorehospitalmanagement.dto.SignupRequestDTO;
import com.study.medicorehospitalmanagement.dto.SignupResponseDTO;
import com.study.medicorehospitalmanagement.entities.User;
import com.study.medicorehospitalmanagement.entities.Patient;
import com.study.medicorehospitalmanagement.entities.type.AuthproviderType;
import com.study.medicorehospitalmanagement.entities.type.RoleType;
import com.study.medicorehospitalmanagement.repositories.PatientRepository;
import com.study.medicorehospitalmanagement.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PatientRepository patientRepository;

    public LoginResponseDTO login(LoginResuestDTO loginResuestDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginResuestDTO.getUsername(), loginResuestDTO.getPassword()));
        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDTO(token, user.getId());
    }

    public User singupInternal(SignupRequestDTO signupResuestDTO, AuthproviderType authproviderType,String providerID) {

        User user = userRepository.findByUsername(signupResuestDTO.getUsername()).orElse(null);
        if (user != null)
            throw new IllegalArgumentException("User already exists");

        user = User.builder()
                .username(signupResuestDTO.getUsername())
                .providerid(providerID)
                .providertype(authproviderType)
                .roles(Set.of(RoleType.PATIENT))
                .build();
        if (authproviderType == AuthproviderType.EMAIL) {
            user.setPassword(passwordEncoder.encode(signupResuestDTO.getPassword()));
        }

        user = userRepository.save(user);

        Patient patient = Patient.builder()
        .name(signupResuestDTO.getName())
        .birthdate(signupResuestDTO.getBirthdate())
        .bloodgroup(signupResuestDTO.getBloodgroup())
        .gender(signupResuestDTO.getGender())
        .email(signupResuestDTO.getEmail())
        .createdat(new Date())
        .user(user)
        .build(); 

        patientRepository.save(patient);

        return user;
    }

    public SignupResponseDTO signup(SignupRequestDTO signupResuestDTO) {

        User user = singupInternal(signupResuestDTO, AuthproviderType.EMAIL, null);
        return new SignupResponseDTO(user.getId(), user.getUsername());
    }

    @Transactional
    public ResponseEntity<LoginResponseDTO> handlerOAuth2Login(OAuth2User oAuth2User, String registerId) {

        AuthproviderType providerType = authUtil.getAuthproviderType(registerId);
        String providerId = authUtil.determineProviderIdFromOAuth2(registerId, oAuth2User);

        User user = userRepository.findByProvideridAndProvidertype(providerId, providerType).orElse(null);

        String email = oAuth2User.getAttribute("email");
        User emailuser = userRepository.findByUsername(email).orElse(null);
        String name = oAuth2User.getAttribute("name");

        if (user == null || emailuser == null) {
            String username = authUtil.determineUsernameFromOAuth2(oAuth2User, registerId, providerId);
            user = singupInternal(new SignupRequestDTO(username, null, name, email, null, null, null), providerType, providerId);
        } else if (user != null) {
            if (email != null && !email.isEmpty() && !email.equals(user.getUsername())) {
                user.setUsername(email);
                userRepository.save(user);
            }
        } else {
            throw new BadCredentialsException("This email is already registered. " + email);
        }

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(authUtil.generateAccessToken(user), user.getId());
        return ResponseEntity.ok(loginResponseDTO);

    }

}
