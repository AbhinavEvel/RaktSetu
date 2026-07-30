package com.rakthsetu.bloodbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rakthsetu.bloodbank.dto.LoginRequestDTO;
import com.rakthsetu.bloodbank.dto.LoginResponseDTO;
import com.rakthsetu.bloodbank.dto.RegisterRequestDTO;
import com.rakthsetu.bloodbank.entity.User;
import com.rakthsetu.bloodbank.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO dto) {
        User savedUser = userService.registerUser(dto);
        savedUser.setPassword(null);   // Response me password kabhi wapas mat bhejo, chahe hashed hi kyun na ho
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        LoginResponseDTO response = userService.loginUser(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
