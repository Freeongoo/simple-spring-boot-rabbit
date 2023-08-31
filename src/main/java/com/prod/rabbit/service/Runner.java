package com.prod.rabbit.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Runner implements CommandLineRunner {

    @Value("${app.name}")
    private String appName;

    @Autowired
    private ProducerService producerService;

    @Override
    public void run(String... args) throws Exception {
        //PriceExchange price = new PriceExchange(appName, "BTC", "USDT", new BigDecimal("1110.2"));
        String message = "message from " + appName;
        log.info(" !!! Being send message: {}", message);
        producerService.broadcastMessage(message);
    }
}
