package com.ust.consumer_service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @RabbitListener(queues = "testQueue")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
