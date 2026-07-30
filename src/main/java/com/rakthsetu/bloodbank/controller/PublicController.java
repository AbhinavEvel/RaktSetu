package com.rakthsetu.bloodbank.controller;

import com.rakthsetu.bloodbank.dto.PublicStockResponseDto;
import com.rakthsetu.bloodbank.service.BloodInventoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @Autowired
    private BloodInventoryService inventoryService;

    // Bina login ke, koi bhi check kar sakta hai stock availability
    @GetMapping("/blood-availability")
    public ResponseEntity<PublicStockResponseDto> checkAvailability(
            @RequestParam String bloodGroup,
            @RequestParam Integer componentId) {

        PublicStockResponseDto response = inventoryService.getPublicAvailability(bloodGroup, componentId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/stock-summary")
    public ResponseEntity<List<PublicStockResponseDto>> getFullStockSummary() {
        List<PublicStockResponseDto> response = inventoryService.getFullPublicStockSummary();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
