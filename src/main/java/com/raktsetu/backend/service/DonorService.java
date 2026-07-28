package com.raktsetu.backend.service;

import org.springframework.stereotype.Service;

import com.raktsetu.backend.dto.DonorRequest;
import com.raktsetu.backend.dto.DonorResponse;
import com.raktsetu.backend.entity.Donor;
import com.raktsetu.backend.entity.User;
import com.raktsetu.backend.repository.DonorRepository;
import com.raktsetu.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DonorService {
	private final DonorRepository donorRepository;
	private final UserRepository userRepository;
	
	public DonorResponse createDonorProfile(String email, DonorRequest request) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));
		if(donorRepository.findByUser_UserId(user.getUserId()).isPresent()) {
			throw new RuntimeException("Donor profile already exists for this user");
		}
		
		Donor donor = new Donor();
		donor.setUser(user);
		donor.setBloodGroup(request.getBloodGroup());
		donor.setAge(request.getAge());
		donor.setCity(request.getCity());
		donor.setState(request.getState());
		donor.setAddress(request.getAddress());
		
		if(request.getIsAvailable() != null) {
			donor.setIsAvailable(request.getIsAvailable());
		}
		
		Donor savedDonor = donorRepository.save(donor);
		
		return mapToResponse(savedDonor);
	}
	
	private DonorResponse mapToResponse(Donor donor) {
		return new DonorResponse(
                donor.getDonorId(),
                donor.getUser().getName(),
                donor.getUser().getEmail(),
                donor.getBloodGroup(),
                donor.getAge(),
                donor.getCity(),
                donor.getState(),
                donor.getAddress(),
                donor.getIsAvailable().name(),
                donor.getLastDonationDate()
        );
	}
	
	public DonorResponse getDonorProfile(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));
		Donor donor = donorRepository.findByUser_UserId(user.getUserId())
				.orElseThrow(() -> new RuntimeException("Donor profile not found"));
		return mapToResponse(donor);
	}
	
	public DonorResponse updateDonorProfile(String email, DonorRequest request) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));
		Donor donor = donorRepository.findByUser_UserId(user.getUserId())
				.orElseThrow(() -> new RuntimeException("Donot profile not found"));
		
		donor.setBloodGroup(request.getBloodGroup());
		donor.setAge(request.getAge());
		donor.setCity(request.getCity());
		donor.setState(request.getState());
		donor.setAddress(request.getAddress());
		
		if(request.getIsAvailable() != null) {
			donor.setIsAvailable(request.getIsAvailable());
		}
		
		Donor updatedDonor = donorRepository.save(donor);
		
		return mapToResponse(updatedDonor);
	}
}
