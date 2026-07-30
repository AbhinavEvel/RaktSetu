package com.rakthsetu.bloodbank.controller;

import com.rakthsetu.bloodbank.dto.BloodInventoryRequestDTO;
import com.rakthsetu.bloodbank.dto.BloodInventoryResponseDTO;
import com.rakthsetu.bloodbank.dto.StatusUpdateRequestDTO;
import com.rakthsetu.bloodbank.service.BloodInventoryService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class BloodInventoryController {

    @Autowired
    private BloodInventoryService inventoryService;

    // Admin naya blood bag add karta hai
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BloodInventoryResponseDTO> addBag(
            @Valid @RequestBody BloodInventoryRequestDTO requestDto) {

        BloodInventoryResponseDTO response = inventoryService.addBag(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Full inventory list — all bags
    @GetMapping("/all")
    public ResponseEntity<List<BloodInventoryResponseDTO>> getAllInventory() {
        List<BloodInventoryResponseDTO> response = inventoryService.getAllInventory();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
 // Bags expiring within 3 days
    @GetMapping("/expiring")
    public ResponseEntity<List<BloodInventoryResponseDTO>> getExpiringBags() {
        List<BloodInventoryResponseDTO> response = inventoryService.getExpiringBags();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PutMapping("/status/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BloodInventoryResponseDTO> updateStatus(
            @PathVariable Integer id,
            @Valid @RequestBody StatusUpdateRequestDTO requestDto) {

        BloodInventoryResponseDTO response = inventoryService.updateStatus(id, requestDto.getStatus());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
 // Poora record update karna
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BloodInventoryResponseDTO> updateInventory(
            @PathVariable Integer id,
            @Valid @RequestBody BloodInventoryRequestDTO requestDto) {

        BloodInventoryResponseDTO response = inventoryService.updateInventory(id, requestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Bag delete karna
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteInventory(@PathVariable Integer id) {
        inventoryService.deleteInventory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
