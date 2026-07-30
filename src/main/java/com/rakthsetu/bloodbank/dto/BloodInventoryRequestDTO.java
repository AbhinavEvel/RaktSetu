package com.rakthsetu.bloodbank.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BloodInventoryRequestDTO {

    @NotBlank(message = "Bag number is required")
    private String bagNumber;

    @NotNull(message = "Blood group is required")
    private String bloodGroup;      // Client se "A+" string aayegi, hum yaha se enum me convert karenge

    @NotNull(message = "Component ID is required")
    private Integer componentId;    // Client sirf ID bhejega, poora BloodComponent object nahi

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than 0")
    private Integer quantityMl;

    @NotNull(message = "Received date is required")
    @PastOrPresent(message = "Received date cannot be in future")
    private LocalDate receivedDate;

    // expiryDate yaha nahi hai — Service layer khud calculate karega (component ke shelf life se)
    // addedBy bhi yaha nahi hai — logged-in admin ki ID JWT token se milegi, client se nahi
}
