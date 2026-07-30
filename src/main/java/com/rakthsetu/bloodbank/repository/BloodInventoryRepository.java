package com.rakthsetu.bloodbank.repository;
import com.rakthsetu.bloodbank.entity.BloodComponent;
import com.rakthsetu.bloodbank.entity.BloodInventory;
import com.rakthsetu.bloodbank.entity.enums.BloodGroup;
import com.rakthsetu.bloodbank.entity.enums.InventoryStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import com.rakthsetu.bloodbank.dto.PublicStockResponseDto;

@Repository
public interface BloodInventoryRepository extends JpaRepository<BloodInventory, Integer> {

    // Ek bag_number se ek hi record milega (unique column hai)
    Optional<BloodInventory> findByBagNumber(String bagNumber);

    // Status ke basis pe filter (e.g. sirf "Available" bags dikhane ke liye)
    List<BloodInventory> findByStatus(InventoryStatus status);

    // Expiry scheduler ke liye — jo bags di gayi date se pehle expire ho rahe hain
    List<BloodInventory> findByExpiryDateBefore(LocalDate date);

    // Admin dashboard ke liye — "expiring within 3 days" wala API isse banega
    List<BloodInventory> findByExpiryDateBetween(LocalDate startDate, LocalDate endDate);

    // Blood group + status dono se filter (combined condition — method naam me "And" use hota hai)
    List<BloodInventory> findByBloodGroupAndStatus(BloodGroup bloodGroup, InventoryStatus status);
    
 // Expiring soon check ke liye — status Available ho aur expiry range me ho
    List<BloodInventory> findByStatusAndExpiryDateBetween(
            InventoryStatus status, LocalDate startDate, LocalDate endDate);
 // Expired ho chuke lekin abhi bhi Available status wale bags dhundne ke liye
    List<BloodInventory> findByStatusAndExpiryDateBefore(InventoryStatus status, LocalDate date);
    
    long countByBloodGroupAndBloodComponentAndStatus(
            BloodGroup bloodGroup, BloodComponent bloodComponent, InventoryStatus status);
    
    @Query("SELECT new com.rakthsetu.bloodbank.dto.PublicStockResponseDto(" +
    	       "CAST(bi.bloodGroup AS string), bi.bloodComponent.componentName, COUNT(bi)) " +
    	       "FROM BloodInventory bi " +
    	       "WHERE bi.status = 'Available' " +
    	       "GROUP BY bi.bloodGroup, bi.bloodComponent.componentName")
    	List<PublicStockResponseDto> getFullStockSummary();
    
 

}
