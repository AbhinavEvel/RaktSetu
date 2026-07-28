package com.raktsetu.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.raktsetu.backend.enums.Role;

import org.springframework.stereotype.Service;

import com.raktsetu.backend.dto.RegisterRequest;
import com.raktsetu.backend.dto.UserResponse;
import com.raktsetu.backend.entity.User;
import com.raktsetu.backend.repository.UserRepository;
import com.raktsetu.backend.dto.AuthResponse;
import com.raktsetu.backend.dto.ChangePasswordRequest;
import com.raktsetu.backend.dto.LoginRequest;
import com.raktsetu.backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	
	public UserResponse registerUser(RegisterRequest request) {
		if(userRepository.existsByEmail(request.getEmail())) {
			throw new RuntimeException("Email already registered!");
		}
		
		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setPhone(request.getPhone());
		user.setRole(Role.valueOf(request.getRole()));
		
		User savedUser = userRepository.save(user);
		
		return mapToResponse(savedUser);
	}
	
	private UserResponse mapToResponse(User user) {
		return new UserResponse(
				user.getUserId(),
				user.getName(),
				user.getEmail(),
				user.getPhone(),
				user.getRole().name(),
				user.getIsVerified().name()
		);
	}
	
	public AuthResponse loginUser(LoginRequest request) {
		User user = userRepository.findByEmail(request.getEmail())
					.orElseThrow(() -> new RuntimeException("Invalid email or password"));
		
		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
	        throw new RuntimeException("Invalid email or password");
	    }
		
		String token = jwtUtil.generateToken(user.getEmail());

	    UserResponse userResponse = mapToResponse(user);
	    return new AuthResponse(token, userResponse);
	}
	
	public void changePassword(String email, ChangePasswordRequest request) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		if(!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
			throw new RuntimeException("Current password is incorrect");
		}
		
		user.setPassword(passwordEncoder.encode(request.getNewPassword()));
		
		userRepository.save(user);
	}
}
