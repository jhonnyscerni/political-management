package br.com.projeto.politicalmanagement.service;

import br.com.projeto.politicalmanagement.models.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface NotificationService {
    Notification saveNotification(Notification notificationModel);

    Page<Notification> findAllNotificationsByUser(UUID userId, Pageable pageable);

    Optional<Notification> findByNotificationIdAndUserId(UUID notificationId, UUID userId);
}
