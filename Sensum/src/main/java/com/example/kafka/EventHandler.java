package com.example.kafka;

import com.example.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventHandler {
    KafkaProducer kafkaProducer;

    @Autowired
    public EventHandler(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }



    //Listens for ClientRegistered event, when detected, posts to topic
    @EventListener
    public void handleClientRegisteredEvent(ClientRegisteredEvent event) {
        Client client = event.client();
        kafkaProducer.postClient(client);
    }
}
