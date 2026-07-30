package com.rakthsetu.bloodbank.entity;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.rakthsetu.bloodbank.entity.enums.BloodGroup;
import com.rakthsetu.bloodbank.entity.enums.InventoryStatus;

@Entity
@Table(name = "blood_inventory")
public class BloodInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer inventoryId;

    @Column(name = "bag_number", nullable = false, unique = true, length = 50)
    private String bagNumber;

    @Column(name = "blood_group", nullable = false)
    private BloodGroup bloodGroup; // Converter yaha automatically apply hoga

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_id", nullable = false)
    private BloodComponent bloodComponent; // FK -> blood_components

    @Column(name = "quantity_ml", nullable = false)
    private Integer quantityMl;

    @Column(name = "received_date", nullable = false)
    private LocalDate receivedDate;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private InventoryStatus status = InventoryStatus.Available; // default value

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "added_by")
    private User addedBy; // FK -> users, nullable

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public BloodInventory() {}

    // Getters & Setters
    public Integer getInventoryId() { return inventoryId; }
    public void setInventoryId(Integer inventoryId) { this.inventoryId = inventoryId; }

    public String getBagNumber() { return bagNumber; }
    public void setBagNumber(String bagNumber) { this.bagNumber = bagNumber; }

    public BloodGroup getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(BloodGroup bloodGroup) { this.bloodGroup = bloodGroup; }

    public BloodComponent getBloodComponent() { return bloodComponent; }
    public void setBloodComponent(BloodComponent bloodComponent) { this.bloodComponent = bloodComponent; }

    public Integer getQuantityMl() { return quantityMl; }
    public void setQuantityMl(Integer quantityMl) { this.quantityMl = quantityMl; }

    public LocalDate getReceivedDate() { return receivedDate; }
    public void setReceivedDate(LocalDate receivedDate) { this.receivedDate = receivedDate; }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public InventoryStatus getStatus() { return status; }
    public void setStatus(InventoryStatus status) { this.status = status; }

    public User getAddedBy() { return addedBy; }
    public void setAddedBy(User addedBy) { this.addedBy = addedBy; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
