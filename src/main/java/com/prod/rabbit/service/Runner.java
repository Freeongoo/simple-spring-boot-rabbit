package com.prod.rabbit.service;

import com.prod.rabbit.dto.PriceExchange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
public class Runner implements CommandLineRunner {

    @Value("${app.name}")
    private String appName;

    @Autowired
    private ProducerService producerService;

    @Override
    public void run(String... args) throws Exception {
        PriceExchange price = new PriceExchange(appName, "BTC", "USDT", new BigDecimal("1110.2"));
        log.info(" !!! Being send message: {}", price);
        producerService.broadcastMessage(price);
    }
}
