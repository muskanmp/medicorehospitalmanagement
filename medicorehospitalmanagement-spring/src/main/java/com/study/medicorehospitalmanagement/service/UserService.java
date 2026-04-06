package com.study.medicorehospitalmanagement.service;

import org.springframework.stereotype.Service;

import com.study.medicorehospitalmanagement.entities.User;
import com.study.medicorehospitalmanagement.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;

    public User SavePassword(User user){
        User existingUser = userRepository.findById(user.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        existingUser.setPassword(user.getPassword());
        return userRepository.save(existingUser);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found with username: " + username));
    }
}
