package com.study.medicorehospitalmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.medicorehospitalmanagement.entities.User;
import com.study.medicorehospitalmanagement.security.AuthUtil;
import com.study.medicorehospitalmanagement.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthUtil authUtil;

    @PostMapping("/save-password")
    public ResponseEntity<User> savePassword(@RequestBody User user) {
        return ResponseEntity.ok(userService.SavePassword(user));
    }

    @GetMapping()
    public User getUserByToken(@RequestHeader("Authorization") String headertoken) {

        String token = headertoken.split("Bearer ")[1];
        String username = authUtil.getUsernameFromToken(token);
        return userService.getUserByUsername(username);
    }

}
