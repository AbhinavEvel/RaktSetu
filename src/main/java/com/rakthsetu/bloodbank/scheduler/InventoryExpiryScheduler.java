package com.rakthsetu.bloodbank.scheduler;

import com.rakthsetu.bloodbank.service.BloodInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class InventoryExpiryScheduler {

    @Autowired
    private BloodInventoryService inventoryService;

    // Har raat midnight (00:00:00) pe chalega
    @Scheduled(cron = "0 0 0 * * *")
    
//    @Scheduled(cron = "*/10 * * * * *") // Har 10 second me chalega (TESTING KE LIYE)
    public void checkAndMarkExpiredBags() {
        inventoryService.markExpiredBags();
    }
    
}
