package com.kosta.order;

import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class OrderEventHandler {

    private static final String ORDER_TOPIC="order-topic";

    private final KafkaProducer kafkaProducer;

    public OrderEventHandler(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @HandleAfterCreate
    public void afterCreateOrder(Order order) {
        kafkaProducer.messageSend(ORDER_TOPIC, order);
    }
}
