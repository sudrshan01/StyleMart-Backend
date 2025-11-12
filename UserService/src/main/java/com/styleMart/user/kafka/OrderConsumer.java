package com.styleMart.user.kafka;

import com.styleMart.user.event.NotificationEvent;
import com.styleMart.user.event.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConsumer {

    private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;

    @KafkaListener(
            topics = "order-notifications",
            groupId = "user-service"
    )
    public void consume(OrderEvent event) {
        if (event == null) {
            System.out.println("⚠️ Received null OrderEvent, skipping...");
            return;
        }

        System.out.println("✅ Received Order Event in User Service: " + event);

        // Convert OrderEvent -> NotificationEvent
        NotificationEvent notificationEvent = new NotificationEvent(
                event.getOrderId(),
                event.getUserId(),
                event.getProductName(),
                event.getQuantity(),
                event.getTotalAmount(),
                event.getStatus(),
                event.getDeliveryDate(),
                event.getPaymentMethod()
        );

        // Send to Notification topic
        kafkaTemplate.send("user-notifications", notificationEvent);
        System.out.println("📤 Sent NotificationEvent to topic user-notifications: " + notificationEvent);
    }
}
