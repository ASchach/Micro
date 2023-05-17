package com.example.kafka;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ClientListener {

    KafkaProducer kafkaProducer;

    public ClientListener(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @EventListener
    public void handleRegisteredEvent(ClientRegistrationEvent clientRegistrationEvent){
        System.out.println("Listener received: " + clientRegistrationEvent.client());
        kafkaProducer.postClient(clientRegistrationEvent.client());
    }

    @EventListener
    public void handleDeletionEvent(ClientDeletionEvent clientDeletionEvent){
        kafkaProducer.deleteClient(clientDeletionEvent.id);
    }
}
