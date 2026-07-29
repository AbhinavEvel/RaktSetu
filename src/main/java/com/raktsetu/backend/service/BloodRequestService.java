package com.raktsetu.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.raktsetu.backend.dto.BloodRequestDTO;
import com.raktsetu.backend.dto.BloodRequestResponseDTO;
import com.raktsetu.backend.entity.BloodRequest;
import com.raktsetu.backend.entity.Patient;
import com.raktsetu.backend.enums.BloodGroup;
import com.raktsetu.backend.enums.Component;
import com.raktsetu.backend.enums.RequestStatus;
import com.raktsetu.backend.enums.Urgency;
import com.raktsetu.backend.repository.BloodRequestRepository;
import com.raktsetu.backend.repository.PatientRepository;


@Service
public class BloodRequestService {

    private final BloodRequestRepository requestRepository;
    private final PatientRepository patientRepository;
    private final BloodStockService bloodStockService;

    public BloodRequestService(BloodRequestRepository requestRepository,
                               PatientRepository patientRepository,
                               BloodStockService bloodStockService) {
        this.requestRepository = requestRepository;
        this.patientRepository = patientRepository;
        this.bloodStockService = bloodStockService;
    }

    public BloodRequestResponseDTO createRequest(Long userId, BloodRequestDTO dto) {

        Patient patient = patientRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        BloodGroup bg = BloodGroup.valueOf(dto.getBloodGroup());
        Component comp = Component.valueOf(dto.getComponent());

        BloodRequest request = new BloodRequest();
        request.setPatient(patient);
        request.setBloodGroup(bg);
        request.setComponent(comp);
        request.setUnitsNeeded(dto.getUnitsNeeded());
        request.setUrgency(Urgency.valueOf(dto.getUrgency()));
        request.setContactNumber(dto.getContactNumber());
        request.setLocationDetails(dto.getLocationDetails());
        request.setAdditionalNote(dto.getAdditionalNote());
        request.setStatus(RequestStatus.PENDING);

        BloodRequest saved = requestRepository.save(request);

        boolean available = bloodStockService.isStockAvailable(bg, comp, dto.getUnitsNeeded());

        if (available) {
            bloodStockService.deductStock(bg, comp, dto.getUnitsNeeded());
            saved.setStatus(RequestStatus.FULFILLED);
            saved = requestRepository.save(saved);
        } else {
            System.out.println("⚠️ Stock unavailable — Donor matching trigger hoga yaha (agle module mein)");
        }

        return mapToDTO(saved);
    }

    public List<BloodRequestResponseDTO> getPatientRequests(Long patientId) {
        return requestRepository.findByPatient_PatientId(patientId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public List<BloodRequestResponseDTO> getAllRequests() {
        return requestRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private BloodRequestResponseDTO mapToDTO(BloodRequest request) {

    	BloodRequestResponseDTO dto = new BloodRequestResponseDTO();

    	dto.setRequestId(request.getRequestId());
    	dto.setBloodGroup(request.getBloodGroup().getLabel());
    	dto.setComponent(request.getComponent().name());
    	dto.setUnitsNeeded(request.getUnitsNeeded());
    	dto.setUrgency(request.getUrgency().name());
    	dto.setContactNumber(request.getContactNumber());
    	dto.setLocationDetails(request.getLocationDetails());
    	dto.setAdditionalNote(request.getAdditionalNote());
    	dto.setStatus(request.getStatus().name());

    	return dto;
             
    }
}