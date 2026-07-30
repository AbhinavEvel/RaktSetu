package com.rakthsetu.bloodbank.mapper;

import com.rakthsetu.bloodbank.dto.NotificationRequestDto;
import com.rakthsetu.bloodbank.dto.NotificationResponseDto;
import com.rakthsetu.bloodbank.entity.Notification;
import com.rakthsetu.bloodbank.entity.NotificationType;

public class NotificationMapper {

    // Entity -> ResponseDto
    public static NotificationResponseDto toResponseDto(Notification entity) {
        NotificationResponseDto dto = new NotificationResponseDto();
        dto.setNotificationId(entity.getNotificationId());
        dto.setUserId(entity.getUser().getUserId());   // nested navigation
        dto.setMessage(entity.getMessage());
        dto.setType(entity.getType().name());          // enum -> "Alert" string
        dto.setIsRead(entity.getIsRead());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    // RequestDto -> Entity (user set nahi karte yahan, Service karega)
    public static Notification toEntity(NotificationRequestDto dto) {
        Notification entity = new Notification();
        entity.setMessage(dto.getMessage());
        entity.setType(NotificationType.valueOf(dto.getType()));   // "Alert" -> enum
        entity.setIsRead(false);   // naya notification hamesha unread
        // user field Service layer set karega (DB se fetch karke)
        return entity;
    }
}
