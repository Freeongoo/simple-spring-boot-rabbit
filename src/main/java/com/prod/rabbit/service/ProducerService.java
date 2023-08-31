package com.prod.rabbit.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Autowired
    private RabbitTemplate template;

    public void broadcastMessage(String message) {
        this.template.convertAndSend("my-exchange", "", message);  // broadcasts string message to each my-queue-* via my-exchange
    }
}
