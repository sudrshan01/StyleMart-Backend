package com.styleMart.order.kafka;

import com.styleMart.order.event.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private static final String TOPIC = "order-notifications";

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void sendOrderEvent(OrderEvent event) {
        if (event != null) {
            kafkaTemplate.send(TOPIC, event);
           // System.out.println("✅ Produced OrderEvent to topic " + TOPIC + ": " + event);
        } else {
          //  System.out.println("⚠️ Skipped producing null OrderEvent");
        }
    }
}
