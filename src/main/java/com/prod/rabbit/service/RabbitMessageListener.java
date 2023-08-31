package com.prod.rabbit.service;

import com.prod.rabbit.config.RabbitConfig;
import com.prod.rabbit.dto.PriceExchange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitMessageListener {

    @RabbitListener(queues = RabbitConfig.queueName)
    public void handle(PriceExchange priceExchange) {
        log.info("Receive message: {}", priceExchange);
    }
}
