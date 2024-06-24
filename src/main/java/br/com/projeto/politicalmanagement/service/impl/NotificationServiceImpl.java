package br.com.projeto.politicalmanagement.service.impl;

import br.com.projeto.politicalmanagement.models.Notification;
import br.com.projeto.politicalmanagement.models.enums.NotificationStatus;
import br.com.projeto.politicalmanagement.repositories.NotificationRepository;
import br.com.projeto.politicalmanagement.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public Notification saveNotification(Notification notificationModel) {
        return notificationRepository.save(notificationModel);
    }

    @Override
    public Page<Notification> findAllNotificationsByUser(UUID userId, Pageable pageable) {
        return notificationRepository.findAllByUserIdAndNotificationStatus(userId, NotificationStatus.CREATED,pageable);
    }

    @Override
    public Optional<Notification> findByNotificationIdAndUserId(UUID notificationId, UUID userId) {
        return notificationRepository.findByNotificationIdAndUserId(notificationId, userId);
    }
}
