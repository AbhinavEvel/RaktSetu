package com.rakthsetu.bloodbank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rakthsetu.bloodbank.dto.LoginRequestDTO;
import com.rakthsetu.bloodbank.dto.LoginResponseDTO;
import com.rakthsetu.bloodbank.dto.RegisterRequestDTO;
import com.rakthsetu.bloodbank.entity.Role;
import com.rakthsetu.bloodbank.entity.User;
import com.rakthsetu.bloodbank.entity.VerificationStatus;
import com.rakthsetu.bloodbank.repository.UserRepository;
import com.rakthsetu.bloodbank.service.UserService;
import com.rakthsetu.bloodbank.util.JwtUtil;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public User registerUser(RegisterRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already registered: " + dto.getEmail());
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setPhone(dto.getPhone());
        user.setRole(Role.valueOf(dto.getRole().toUpperCase()));
        user.setVerificationStatus(VerificationStatus.Pending);

        return userRepository.save(user);
    }

    @Override
    public LoginResponseDTO loginUser(LoginRequestDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        // Updated line — userId bhi pass kiya
        return new LoginResponseDTO(user.getUserId(), token, user.getEmail(), user.getRole().name(), "Login successful");
    
    }
}
