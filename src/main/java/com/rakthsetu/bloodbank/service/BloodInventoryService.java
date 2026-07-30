package com.rakthsetu.bloodbank.service;
import com.rakthsetu.bloodbank.entity.Role;
import com.rakthsetu.bloodbank.entity.User;
import com.rakthsetu.bloodbank.service.NotificationService;
import com.rakthsetu.bloodbank.dto.BloodInventoryRequestDTO;

import com.rakthsetu.bloodbank.dto.BloodInventoryResponseDTO;
import com.rakthsetu.bloodbank.dto.PublicStockResponseDto;
import com.rakthsetu.bloodbank.entity.BloodComponent;
import com.rakthsetu.bloodbank.entity.BloodInventory;
import com.rakthsetu.bloodbank.entity.User;
import com.rakthsetu.bloodbank.entity.enums.BloodGroup;
import com.rakthsetu.bloodbank.entity.enums.InventoryStatus;
import com.rakthsetu.bloodbank.exception.ResourceNotFoundException;
import com.rakthsetu.bloodbank.mapper.BloodInventoryMapper;
import com.rakthsetu.bloodbank.repository.BloodComponentRepository;
import com.rakthsetu.bloodbank.repository.BloodInventoryRepository;
import com.rakthsetu.bloodbank.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BloodInventoryService {

    @Autowired
    private BloodInventoryRepository inventoryRepository;
    
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private BloodComponentRepository componentRepository;

    @Autowired
    private UserRepository userRepository;

    public BloodInventoryResponseDTO addBag(BloodInventoryRequestDTO requestDto) {

        // Step A: componentId se poora BloodComponent fetch karo
        BloodComponent component = componentRepository.findById(requestDto.getComponentId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Blood component not found with id: " + requestDto.getComponentId()));

        // Step B: RequestDto -> Entity (basic fields, Mapper se)
        BloodInventory inventory = BloodInventoryMapper.toEntity(requestDto);

        // Step C: relationship set karo (Mapper me nahi kiya tha, yaha kar rahe)
        inventory.setBloodComponent(component);

        // Step D: BUSINESS LOGIC - expiry date calculate karo shelf life se
        inventory.setExpiryDate(
                requestDto.getReceivedDate().plusDays(component.getShelfLifeDays())
        );

        // Step E: currently logged-in admin ka User object nikaalo
        User currentAdmin = getCurrentLoggedInUser();
        inventory.setAddedBy(currentAdmin);

        // Step F: DB me save karo
        BloodInventory saved = inventoryRepository.save(inventory);

        // Step G: saved Entity -> ResponseDto
        return BloodInventoryMapper.toResponseDto(saved);
    }

    public List<BloodInventoryResponseDTO> getAllInventory() {
        return inventoryRepository.findAll()
                .stream()
                .map(BloodInventoryMapper::toResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<BloodInventoryResponseDTO> getExpiringBags() {
        LocalDate today = LocalDate.now();
        LocalDate threeDaysLater = today.plusDays(3);

        List<BloodInventory> expiringBags = inventoryRepository
                .findByStatusAndExpiryDateBetween(InventoryStatus.Available, today, threeDaysLater);

        return expiringBags.stream()
                .map(BloodInventoryMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // Helper method - JWT se authenticate hua current user nikalta hai
    private User getCurrentLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();   // JWT filter yaha email/username set karta hai
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Logged in user not found"));
    }
    
    public BloodInventoryResponseDTO updateStatus(Integer inventoryId, String newStatusStr) {

        // Step A: Bag ID se existing record dhundo
        BloodInventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Blood bag not found with id: " + inventoryId));

        // Step B: String -> Enum convert karo
        InventoryStatus newStatus;
        try {
            newStatus = InventoryStatus.valueOf(newStatusStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status value: " + newStatusStr);
        }

        // Step C: BUSINESS RULE - Expired manually set nahi kar sakte
        if (newStatus == InventoryStatus.Expired) {
            throw new IllegalArgumentException("Expired status is set automatically by the system, not manually");
        }

        // Step D: BUSINESS RULE - already Used/Expired/Discarded bag ko wapas Available nahi kar sakte
        if (inventory.getStatus() != InventoryStatus.Available && newStatus == InventoryStatus.Available) {
            throw new IllegalStateException("Cannot mark a " + inventory.getStatus() + " bag as Available again");
        }

        // Step E: Status update karo aur save karo
        inventory.setStatus(newStatus);
        BloodInventory updated = inventoryRepository.save(inventory);

        return BloodInventoryMapper.toResponseDto(updated);
    }
 // Poora record update karna (bag number, blood group, quantity, etc. — sab editable)
    public BloodInventoryResponseDTO updateInventory(Integer inventoryId, BloodInventoryRequestDTO requestDto) {

        BloodInventory existing = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Blood bag not found with id: " + inventoryId));

        BloodComponent component = componentRepository.findById(requestDto.getComponentId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Blood component not found with id: " + requestDto.getComponentId()));

        // Fields update karo (existing object pe hi, naya object nahi banate)
        existing.setBagNumber(requestDto.getBagNumber());
        existing.setBloodGroup(BloodGroup.fromDbValue(requestDto.getBloodGroup()));
        existing.setBloodComponent(component);
        existing.setQuantityMl(requestDto.getQuantityMl());
        existing.setReceivedDate(requestDto.getReceivedDate());
        existing.setExpiryDate(requestDto.getReceivedDate().plusDays(component.getShelfLifeDays()));

        BloodInventory updated = inventoryRepository.save(existing);
        return BloodInventoryMapper.toResponseDto(updated);
    }

    // Bag delete karna
    public void deleteInventory(Integer inventoryId) {
        BloodInventory existing = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Blood bag not found with id: " + inventoryId));

        inventoryRepository.delete(existing);
    }
    public void markExpiredBags() {

        LocalDate today = LocalDate.now();

        // Step A: Expired ho chuke Available bags dhundo
        List<BloodInventory> expiredBags = inventoryRepository
                .findByStatusAndExpiryDateBefore(InventoryStatus.Available, today);

        if (expiredBags.isEmpty()) {
            return;   // Kuch bhi expire nahi hua, kaam khatam
        }

        // Step B: Har bag ka status "Expired" set karo
        for (BloodInventory bag : expiredBags) {
            bag.setStatus(InventoryStatus.Expired);
        }
        inventoryRepository.saveAll(expiredBags);   // Ek saath saare update, efficient

        // Step C: Saare Admins ko notification bhejo
        List<User> admins = userRepository.findByRole(Role.ADMIN);
        String message = expiredBags.size() + " blood bag(s) have expired and been marked as Expired.";

        for (User admin : admins) {
            notificationService.createNotificationInternal(
                    admin.getUserId(), message, "System");
        }
    }
    public PublicStockResponseDto getPublicAvailability(String bloodGroupStr, Integer componentId) {

        BloodGroup bloodGroup;
        try {
            bloodGroup = BloodGroup.fromDbValue(bloodGroupStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid blood group: " + bloodGroupStr);
        }

        BloodComponent component = componentRepository.findById(componentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Blood component not found with id: " + componentId));

        long units = inventoryRepository.countByBloodGroupAndBloodComponentAndStatus(
                bloodGroup, component, InventoryStatus.Available);

        return new PublicStockResponseDto(bloodGroupStr, component.getComponentName(), units);
    }
    
    public List<PublicStockResponseDto> getFullPublicStockSummary() {
        return inventoryRepository.getFullStockSummary();   // seedha return, koi conversion nahi chahiye
    }
}
