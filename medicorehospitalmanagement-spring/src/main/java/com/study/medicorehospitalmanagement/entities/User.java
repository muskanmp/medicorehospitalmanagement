package com.study.medicorehospitalmanagement.entities;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.study.medicorehospitalmanagement.entities.type.AuthproviderType;
import com.study.medicorehospitalmanagement.entities.type.RoleType;

import java.util.*;
import java.util.stream.Collectors;

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

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    Set<RoleType> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return roles.stream()
        .map(role-> new SimpleGrantedAuthority("ROLE_"+ role.name()))
        .collect(Collectors.toSet());
    }

    
}
