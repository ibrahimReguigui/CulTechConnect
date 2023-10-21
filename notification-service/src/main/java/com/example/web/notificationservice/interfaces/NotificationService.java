package com.example.web.notificationservice.interfaces;

import com.example.web.notificationservice.entitie.Notification;

import java.security.Principal;
import java.util.List;

public interface NotificationService {

    Notification saveNotification(Notification notification);
    List<Notification> getMyNotification(Principal principal);

    void setSeen();
}
