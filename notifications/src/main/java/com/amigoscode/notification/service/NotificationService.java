package com.amigoscode.notification.service;

import com.amigoscode.clients.notification.NotificationRequest;
import com.amigoscode.notification.entity.Notification;
import com.amigoscode.notification.persistence.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class NotificationService {

  private final NotificationRepository notificationRepository;

  public void send(final NotificationRequest notificationRequest) {

    final Notification notification =
        Notification.builder()
            .toCustomerId(notificationRequest.toCustomerId())
            .toCustomerEmail(notificationRequest.toCustomerEmail())
            .sender("Amigoscode")
            .message(notificationRequest.message())
            .sentAt(LocalDateTime.now())
            .build();

    notificationRepository.save(notification);
    log.info("Notification saved");
  }
}
