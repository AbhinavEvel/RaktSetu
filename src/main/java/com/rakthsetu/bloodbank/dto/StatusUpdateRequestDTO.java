package com.rakthsetu.bloodbank.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusUpdateRequestDTO {

    @NotBlank(message = "Status is required")
    private String status;   // Client "Used" ya "Discarded" bhejega (string)
}
