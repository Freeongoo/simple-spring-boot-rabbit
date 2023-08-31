package com.prod.rabbit.service;

import com.prod.rabbit.dto.PriceExchange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerService {

    @Bean
    public MessageListenerAdapter listenerAdapter(ConsumerService consumerService, MessageConverter messageConverter) {
        MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(consumerService, messageConverter);
        listenerAdapter.setDefaultListenerMethod("handle");     // see below
        return listenerAdapter;
    }

    public void handle(PriceExchange message) {
        log.info("Income message: " + message);
    }
}
