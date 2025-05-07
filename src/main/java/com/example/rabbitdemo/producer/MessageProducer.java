package com.example.rabbitdemo.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rabbitdemo.config.RabbitMQConstants;

@Service
public class MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(
          RabbitMQConstants.EXCHANGE_NAME,
          RabbitMQConstants.ROUTING_KEY,
            message
        );
    }
} 