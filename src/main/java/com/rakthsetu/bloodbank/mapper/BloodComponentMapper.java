package com.rakthsetu.bloodbank.mapper;

import com.rakthsetu.bloodbank.dto.BloodComponentDTO;
import com.rakthsetu.bloodbank.entity.BloodComponent;

public class BloodComponentMapper {

    // Entity -> DTO (database se aaya data, client ko bhejne layak banao)
    public static BloodComponentDTO toDTO(BloodComponent entity) {
        BloodComponentDTO dto = new BloodComponentDTO();
        dto.setComponentId(entity.getComponentId());
        dto.setComponentName(entity.getComponentName());
        dto.setComponentName(entity.getComponentCode());
        dto.setShelfLifeDays(entity.getShelfLifeDays());
        dto.setDescription(entity.getDescription());
        dto.setIsActive(entity.getIsActive());
        return dto;
    }

    // DTO -> Entity (client se aaya data, database me save karne layak banao)
    public static BloodComponent toEntity(BloodComponentDTO dto) {
        BloodComponent entity = new BloodComponent();
        entity.setComponentId(dto.getComponentId());
        entity.setComponentName(dto.getComponentName());
        entity.setComponentCode(dto.getComponentCode());
        entity.setShelfLifeDays(dto.getShelfLifeDays());
        entity.setDescription(dto.getDescription());
        entity.setIsActive(dto.getIsActive());
        return entity;
    }
}
