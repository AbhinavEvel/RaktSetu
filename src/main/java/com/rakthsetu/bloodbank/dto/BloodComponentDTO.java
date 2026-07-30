package com.rakthsetu.bloodbank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BloodComponentDTO {

    private Integer componentId;   // Response me chahiye, request me client nahi bhejta (auto-generated)

    @NotBlank(message = "Component name is required")
    private String componentName;

    @NotBlank(message = "Component code is required")
    private String componentCode;

    @NotNull(message = "Shelf life is required")
    @Positive(message = "Shelf life must be a positive number")
    private Integer shelfLifeDays;

    private String description;

    private Boolean isActive;

	
}
