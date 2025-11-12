package com.styleMart.notification.kafka;

import com.styleMart.notification.event.NotificationEvent;
import com.styleMart.notification.model.Notification;
import com.styleMart.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationService notificationService;

    @KafkaListener(
            topics = "user-notifications",       // ✅ topic name
            groupId = "notification-service"     // ✅ group id (matches YAML)
    )
    public void consume(NotificationEvent event) {
        if (event == null) {
            System.out.println("⚠️ Received null event, ignoring...");
            return;
        }

        // ✅ Convert NotificationEvent → Notification entity
        Notification notification = new Notification();
        notification.setOrderId(event.getOrderId());
        notification.setUserId(event.getUserId());
        notification.setProductName(event.getProductName());
        notification.setQuantity(event.getQuantity());
        notification.setTotalAmount(event.getTotalAmount());
        notification.setStatus(event.getStatus());
        notification.setDeliveryDate(event.getDeliveryDate());
        notification.setPaymentMethod(event.getPaymentMethod());

        // ✅ Save notification in DB
        notificationService.saveNotification(notification);

        System.out.println("✅ Notification saved for order: " + event.getOrderId());
    }
}
