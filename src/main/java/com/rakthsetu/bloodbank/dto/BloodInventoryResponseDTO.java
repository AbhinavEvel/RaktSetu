package com.rakthsetu.bloodbank.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BloodInventoryResponseDTO {

    private Integer inventoryId;
    private String bagNumber;
    private String bloodGroup;          // Enum -> readable String ("A+")
    private String componentName;       // Poora BloodComponent object nahi — sirf naam
    private String componentCode;
    private Integer quantityMl;
    private LocalDate receivedDate;
    private LocalDate expiryDate;
    private String status;
    private String addedByName;         // Poora User object nahi — sirf naam
    private LocalDateTime createdAt;
}
