package com.example.web.notificationservice.service;

import com.example.web.notificationservice.entitie.Notification;
import com.example.web.notificationservice.interfaces.NotificationService;
import com.example.web.notificationservice.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class NotificationServiceImp implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Override
    public Notification saveNotification(Notification notification) {
        notification.setSeen(false);
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getMyNotification(Principal principal) {
        return notificationRepository.findAll().stream().filter(e->e.getUserId().equals(principal.getName())).toList();
    }

    @Override
    public void setSeen() {
        notificationRepository.findAll().stream().filter(e->!e.getSeen()).forEach(e -> {
            e.setSeen(true);
            notificationRepository.save(e);
        });
    }
}
