package com.study.medicorehospitalmanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor

public class WebSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // httpSecurity.formLogin(formConfig -> formConfig. )  //Login can be Configured here. 
        httpSecurity
        .csrf(csrfConfig-> csrfConfig.disable())
        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth->auth
            . requestMatchers("/public/**", "/auth/**").permitAll()
            // .requestMatchers("/admin/**").hasRole("ADMIN")
            // .requestMatchers("/doctors/**").hasAnyRole("DOCTOR", "ADMIN")
            .anyRequest().authenticated()
    )
    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        // .formLogin( Customizer.withDefaults());
        return httpSecurity.build();
    }

    // @Bean
    UserDetailsService detailsService (){
        UserDetails userDetails = User.withUsername("admin")
        .password(passwordEncoder.encode("user123")).roles("ADMIN").build();
        
        UserDetails userDetails2 = User.withUsername("patient")
        .password(passwordEncoder.encode("user456")).roles("PATIENT").build();

        return new InMemoryUserDetailsManager(userDetails, userDetails2);
    }
}
