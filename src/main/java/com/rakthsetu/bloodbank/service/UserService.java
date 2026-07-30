package com.rakthsetu.bloodbank.service;

import com.rakthsetu.bloodbank.dto.LoginRequestDTO;
import com.rakthsetu.bloodbank.dto.LoginResponseDTO;
import com.rakthsetu.bloodbank.dto.RegisterRequestDTO;
import com.rakthsetu.bloodbank.entity.User;

public interface UserService {
    User registerUser(RegisterRequestDTO dto);
    LoginResponseDTO loginUser(LoginRequestDTO dto);   // Naya method
}
