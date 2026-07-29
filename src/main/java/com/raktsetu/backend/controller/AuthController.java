package com.raktsetu.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raktsetu.backend.dto.AuthResponse;
import com.raktsetu.backend.dto.ChangePasswordRequest;
import com.raktsetu.backend.dto.LoginRequest;
import com.raktsetu.backend.dto.RegisterRequest;
import com.raktsetu.backend.dto.UserResponse;
import com.raktsetu.backend.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	private final UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request){
		UserResponse response = userService.registerUser(request);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
	    System.out.println("LOGIN METHOD CALLED - Email: " + request.getEmail());
	    AuthResponse response = userService.loginUser(request);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/change-password")
	public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordRequest request,
			Authentication authentication){
		String email = authentication.getName();
		userService.changePassword(email, request);
		return new ResponseEntity<>("Password changed successfully", HttpStatus.OK); 
	}
}
