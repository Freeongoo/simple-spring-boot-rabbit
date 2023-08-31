package com.prod.rabbit.service;

import com.prod.rabbit.config.RabbitConfig;
import com.prod.rabbit.dto.PriceExchange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitPriceSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(PriceExchange priceExchange) {
        rabbitTemplate.convertAndSend(RabbitConfig.queueName, priceExchange);
    }
}
