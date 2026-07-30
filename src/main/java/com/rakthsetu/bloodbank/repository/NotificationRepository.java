package com.rakthsetu.bloodbank.repository;

import com.rakthsetu.bloodbank.entity.Notification;
import com.rakthsetu.bloodbank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    // User ki saari notifications, sabse nayi pehle
    List<Notification> findByUserOrderByCreatedAtDesc(User user);

    // Unread notifications count (badge ke liye)
    long countByUserAndIsRead(User user, Boolean isRead);
}
