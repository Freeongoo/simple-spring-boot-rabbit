package com.prod.rabbit.service;

import com.prod.rabbit.config.ExchangeTopic;
import com.prod.rabbit.dto.PriceExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Autowired
    private RabbitTemplate template;

    public void broadcastMessage(PriceExchange priceExchange) {
        this.template.convertAndSend(ExchangeTopic.TOPIC, "", priceExchange);  // broadcasts string message to each my-queue-* via my-exchange
    }
}
