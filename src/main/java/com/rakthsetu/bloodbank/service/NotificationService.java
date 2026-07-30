package com.rakthsetu.bloodbank.service;

import com.rakthsetu.bloodbank.dto.NotificationRequestDto;
import com.rakthsetu.bloodbank.dto.NotificationResponseDto;
import com.rakthsetu.bloodbank.entity.Notification;
import com.rakthsetu.bloodbank.entity.NotificationType;
import com.rakthsetu.bloodbank.entity.User;
import com.rakthsetu.bloodbank.exception.ResourceNotFoundException;
import com.rakthsetu.bloodbank.mapper.NotificationMapper;
import com.rakthsetu.bloodbank.repository.NotificationRepository;
import com.rakthsetu.bloodbank.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    // Controller se (JSON body ke through) call hoga
    public NotificationResponseDto createNotification(NotificationRequestDto requestDto) {
        Notification saved = createNotificationInternal(
                requestDto.getUserId(), requestDto.getMessage(), requestDto.getType());
        return NotificationMapper.toResponseDto(saved);
    }

    // REUSABLE method — agle module (emergency_alerts) yahi seedha Java se call karega,
    // bina HTTP request ke. Isi wajah se "Internal" naam diya aur poori Entity return ki (DTO nahi),
    // taaki calling module ko notification.getNotificationId() jaisi cheez bhi mil sake agar chahiye ho.
    public Notification createNotificationInternal(Integer userId, String message, String typeStr) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        notification.setType(NotificationType.valueOf(typeStr));
        notification.setIsRead(false);

        return notificationRepository.save(notification);
    }

    // Ek user ki saari notifications, latest pehle
    public List<NotificationResponseDto> getNotificationsByUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        return notificationRepository.findByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(NotificationMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // Ek specific notification ko "read" mark karna
    public NotificationResponseDto markAsRead(Integer notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Notification not found with id: " + notificationId));

        notification.setIsRead(true);   // idempotent - already true ho to bhi koi harm nahi
        Notification updated = notificationRepository.save(notification);
        return NotificationMapper.toResponseDto(updated);
    }

    // Unread count (badge ke liye)
    public long getUnreadCount(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        return notificationRepository.countByUserAndIsRead(user, false);
    }
}
