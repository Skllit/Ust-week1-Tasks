package com.ust.producer_service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private static final String EXCHANGE_NAME = "testExchange";
    private static final String ROUTING_KEY = "routing.key";

    @GetMapping("/send")
    public String sendMessage() {
        String message = "thats it message recieved!";
        amqpTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message);
        return "Message sent: " + message;
    }
}
