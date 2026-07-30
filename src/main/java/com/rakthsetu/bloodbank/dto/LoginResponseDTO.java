package com.rakthsetu.bloodbank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseDTO {
	private Integer userId;
    private String token;
    private String email;
    private String role;
    private String message;
}
