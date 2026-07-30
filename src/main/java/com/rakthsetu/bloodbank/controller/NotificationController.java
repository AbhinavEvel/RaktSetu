package com.rakthsetu.bloodbank.controller;

import com.rakthsetu.bloodbank.dto.NotificationRequestDto;
import com.rakthsetu.bloodbank.dto.NotificationResponseDto;
import com.rakthsetu.bloodbank.service.NotificationService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Naya notification create karna
    @PostMapping("/create")
    public ResponseEntity<NotificationResponseDto> create(
            @Valid @RequestBody NotificationRequestDto requestDto) {
        NotificationResponseDto response = notificationService.createNotification(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // User ki saari notifications
    @GetMapping("/{userId}")
    public ResponseEntity<List<NotificationResponseDto>> getByUser(@PathVariable Integer userId) {
        List<NotificationResponseDto> response = notificationService.getNotificationsByUser(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Ek notification ko read mark karna
    @PutMapping("/read/{id}")
    public ResponseEntity<NotificationResponseDto> markAsRead(@PathVariable Integer id) {
        NotificationResponseDto response = notificationService.markAsRead(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Unread count
    @GetMapping("/unread/{userId}")
    public ResponseEntity<Map<String, Long>> getUnreadCount(@PathVariable Integer userId) {
        long count = notificationService.getUnreadCount(userId);
        return new ResponseEntity<>(Map.of("unreadCount", count), HttpStatus.OK);
    }
}
