package com.rakthsetu.bloodbank.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicStockResponseDto {
    private String bloodGroup;
    private String componentName;
    private long unitsAvailable;
}
