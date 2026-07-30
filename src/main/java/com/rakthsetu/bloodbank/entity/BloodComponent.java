package com.rakthsetu.bloodbank.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity                              // Batata hai: yeh class ek database table se mapped hai
@Table(name = "blood_components")    // Exact table name (agar class name aur table name alag ho toh yeh zaroori hai)
@Getter                              // Lombok: saare fields ke getters auto-generate karega
@Setter                              // Lombok: saare fields ke setters auto-generate karega
@NoArgsConstructor                   // Lombok: empty constructor — Hibernate ko YEH hamesha chahiye
@AllArgsConstructor                  // Lombok: saare fields wala constructor
public class BloodComponent {
	
	

    @Id                                                     // Primary Key batata hai
    @GeneratedValue(strategy = GenerationType.IDENTITY)      // DB khud AUTO_INCREMENT karega, Java nahi
    @Column(name = "component_id")
    private Integer componentId;

    @Column(name = "component_name", nullable = false, unique = true, length = 100)
    private String componentName;

    @Column(name = "component_code", nullable = false, unique = true, length = 30)
    private String componentCode;

    @Column(name = "shelf_life_days", nullable = false)
    private Integer shelfLifeDays;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;


}
