package com.raktsetu.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.raktsetu.backend.dto.BloodRequestDTO;
import com.raktsetu.backend.dto.BloodRequestResponseDTO;
import com.raktsetu.backend.security.CustomUserDetails;
import com.raktsetu.backend.service.BloodRequestService;

@RestController
@RequestMapping("/api/requests")
public class BloodRequestController {

    private final BloodRequestService requestService;

    public BloodRequestController(BloodRequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/create")
    public ResponseEntity<BloodRequestResponseDTO> create(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody BloodRequestDTO dto) {

        Long userId = ((CustomUserDetails) userDetails).getUserId();

        BloodRequestResponseDTO response =
                requestService.createRequest(userId, dto);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<List<BloodRequestResponseDTO>> getByPatient(
            @PathVariable Long id) {

        return ResponseEntity.ok(requestService.getPatientRequests(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BloodRequestResponseDTO>> getAll() {

        return ResponseEntity.ok(requestService.getAllRequests());
    }
}
