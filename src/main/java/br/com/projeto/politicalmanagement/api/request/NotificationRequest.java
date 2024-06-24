package br.com.projeto.politicalmanagement.api.request;

import br.com.projeto.politicalmanagement.models.enums.NotificationStatus;

import javax.validation.constraints.NotNull;

public class NotificationRequest {

    @NotNull
    private NotificationStatus notificationStatus;

    public NotificationStatus getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(NotificationStatus notificationStatus) {
        this.notificationStatus = notificationStatus;
    }
}
