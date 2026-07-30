package com.rakthsetu.bloodbank.mapper;

import com.rakthsetu.bloodbank.dto.BloodInventoryRequestDTO;
import com.rakthsetu.bloodbank.dto.BloodInventoryResponseDTO;
import com.rakthsetu.bloodbank.entity.BloodInventory;
import com.rakthsetu.bloodbank.entity.enums.BloodGroup;
import com.rakthsetu.bloodbank.entity.enums.InventoryStatus;

public class BloodInventoryMapper {

    // Entity -> ResponseDto (DB se aaya data, client ko bhejne layak banao)
    public static BloodInventoryResponseDTO toResponseDto(BloodInventory entity) {
        BloodInventoryResponseDTO dto = new BloodInventoryResponseDTO();
        dto.setInventoryId(entity.getInventoryId());
        dto.setBagNumber(entity.getBagNumber());
        dto.setBloodGroup(entity.getBloodGroup().getDbValue());  // enum -> "A+" string

        // Nested object se value nikaal rahe hain (BloodComponent relationship)
        dto.setComponentName(entity.getBloodComponent().getComponentName());
        dto.setComponentCode(entity.getBloodComponent().getComponentCode());

        dto.setQuantityMl(entity.getQuantityMl());
        dto.setReceivedDate(entity.getReceivedDate());
        dto.setExpiryDate(entity.getExpiryDate());
        dto.setStatus(entity.getStatus().name());   // enum -> "Available" string

        // addedBy nullable hai (ON DELETE SET NULL), isliye null-check zaroori
        if (entity.getAddedBy() != null) {
            dto.setAddedByName(entity.getAddedBy().getName());
        } else {
            dto.setAddedByName(null);
        }

        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    // RequestDto -> Entity (client se aaya data, save karne layak banao)
    // Note: bloodComponent aur addedBy yaha SET NAHI karte — Service layer karega (DB fetch chahiye)
    public static BloodInventory toEntity(BloodInventoryRequestDTO dto) {
        BloodInventory entity = new BloodInventory();
        entity.setBagNumber(dto.getBagNumber());
        entity.setBloodGroup(BloodGroup.fromDbValue(dto.getBloodGroup()));  // "A+" -> enum
        entity.setQuantityMl(dto.getQuantityMl());
        entity.setReceivedDate(dto.getReceivedDate());
        entity.setStatus(InventoryStatus.Available);  // naya bag hamesha Available se start hota hai
        // expiryDate, bloodComponent, addedBy — Service layer set karega
        return entity;
    }
}
