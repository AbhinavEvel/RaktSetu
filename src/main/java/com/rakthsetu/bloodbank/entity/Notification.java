package com.rakthsetu.bloodbank.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Integer notificationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;   // FK -> users, jiske liye yeh notification hai

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private NotificationType type = NotificationType.System;   // default DB jaisa hi

    @Column(name = "is_read")
    private Boolean isRead = false;   // default DB jaisa hi (0 = false)

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
}
