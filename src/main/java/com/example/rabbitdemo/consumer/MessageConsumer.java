package com.example.rabbitdemo.consumer;

import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
} 