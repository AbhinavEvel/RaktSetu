package com.raktsetu.backend.dto;

import java.time.LocalDate;

import com.raktsetu.backend.enums.BloodGroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonorResponse {
	private Integer donorId;
	private String name;
	private String email;
	private BloodGroup bloodGroup;
	private Integer age;
	private String city;
	private String state;
	private String address;
	private String isAvailable;
	private LocalDate lastDonationDate;
}
