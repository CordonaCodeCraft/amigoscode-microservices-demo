package com.amigoscode.notification.persistence;

import com.amigoscode.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {}
