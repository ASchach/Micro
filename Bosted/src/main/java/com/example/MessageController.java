package com.example;

import org.springframework.kafka.core.KafkaTemplate;

public class MessageController {
    public KafkaTemplate<String, String> kafkaTemplate;

    public MessageController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
}
