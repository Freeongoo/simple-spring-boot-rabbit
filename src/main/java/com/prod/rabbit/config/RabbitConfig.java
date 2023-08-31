package com.prod.rabbit.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
@EnableRabbit
public class RabbitConfig {

    public static final String MY_QUEUE = "my-queue-";

    @Bean
    public String instanceId() {
        return "id-" + UUID.randomUUID();
    }

    @Bean
    public FanoutExchange broadcastExchange() {
        return new FanoutExchange(ExchangeTopic.TOPIC);
    }

    @Bean
    public Queue instanceQueue(String instanceId) {
        return new Queue(MY_QUEUE + instanceId, false);
    }

    @Bean
    public Binding instanceBinding(Queue instanceQueue, FanoutExchange broadcastExchange) {
        return BindingBuilder.bind(instanceQueue).to(broadcastExchange);
    }

    @Bean
    public SimpleMessageListenerContainer container(String instanceId, ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(MY_QUEUE + instanceId);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter messageConverter(ObjectMapper jsonMapper) {
        return new Jackson2JsonMessageConverter(jsonMapper);
    }
}
