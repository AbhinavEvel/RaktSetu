package com.raktsetu.backend.controller;


import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.raktsetu.backend.dto.PatientRequestDTO;
import com.raktsetu.backend.dto.PatientResponseDTO;
import com.raktsetu.backend.service.PatientInterface.PatientService;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    @GetMapping("/profile")
    public PatientResponseDTO getProfile(Authentication authentication) {
        return patientService.getPatientProfile(authentication.getName());
    }

    @PutMapping("/profile/update")
    public PatientResponseDTO updateProfile(Authentication authentication,
                                            @RequestBody PatientRequestDTO requestDTO) {
        return patientService.updatePatientProfile(authentication.getName(), requestDTO);
    }

    @GetMapping("/all")
    public List<PatientResponseDTO> getAllPatients() {
        return patientService.getAllPatients();
    }
}