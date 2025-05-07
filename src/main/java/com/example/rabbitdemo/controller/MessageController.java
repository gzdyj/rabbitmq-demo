package com.example.rabbitdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rabbitdemo.producer.MessageProducer;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageProducer messageProducer;

    @PostMapping
    public String sendMessage(@RequestBody String message) {
        messageProducer.sendMessage(message);
        return "Message sent successfully";
    }
} 