package com.study.medicorehospitalmanagement.entities;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.study.medicorehospitalmanagement.entities.type.AuthproviderType;

import java.util.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Table(name="user", indexes = {
    @Index(name= "idx_provider_id_provider_type", columnList = "providerid, providertype")
})

public class User implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(unique = true)
    private String username;

    private String password;

    private String email;
    private String providerid;

    @Enumerated(EnumType.STRING)
    private AuthproviderType providertype;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of();
    }

    
}
