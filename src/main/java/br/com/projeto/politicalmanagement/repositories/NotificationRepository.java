package br.com.projeto.politicalmanagement.repositories;

import br.com.projeto.politicalmanagement.models.Notification;
import br.com.projeto.politicalmanagement.models.enums.NotificationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    Page<Notification> findAllByUserIdAndNotificationStatus(UUID userId, NotificationStatus notificationStatus, Pageable pageable);

    Optional<Notification> findByNotificationIdAndUserId(UUID notificationId, UUID userId);
}
