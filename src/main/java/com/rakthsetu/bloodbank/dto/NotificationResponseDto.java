package com.rakthsetu.bloodbank.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponseDto {

    private Integer notificationId;
    private Integer userId;         // poora User object nahi, sirf ID (halka response)
    private String message;
    private String type;
    private Boolean isRead;
    private LocalDateTime createdAt;
}
