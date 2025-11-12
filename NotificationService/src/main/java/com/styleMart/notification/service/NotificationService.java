package com.styleMart.notification.service;

import com.styleMart.notification.exception.NotificationNotFoundException;
import com.styleMart.notification.model.Notification;
import com.styleMart.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    // Create or save notification
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    // Get notification by id
    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException(id));
    }

    // Get all notifications
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // Get notifications by userId
    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    // Update notification (mark as read)
    public Notification markAsRead(Long id) {
        Notification notification = getNotificationById(id);

        return notificationRepository.save(notification);
    }

    // Delete notification
    public void deleteNotification(Long id) {
        if (!notificationRepository.existsById(id)) {
            throw new NotificationNotFoundException(id);
        }
        notificationRepository.deleteById(id);
    }
}
