package com.example.kafka;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ClientListener {

    KafkaProducer kafkaProducer;

    public ClientListener(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    //ClientRegistrationEvent
    @EventListener
    public void handleRegisteredEvent(ClientRegistrationEvent clientRegistrationEvent){
        System.out.println("Listener received: " + clientRegistrationEvent.client());
        kafkaProducer.postClient(clientRegistrationEvent.client());
    }

    //ClientDeletionEvent
    @EventListener
    public void handleDeletionEvent(ClientDeletionEvent clientDeletionEvent){
        kafkaProducer.deleteClient(clientDeletionEvent.id);
    }

    //ClientUpdateEvent
    @EventListener
    public void handleUpdateEvent(ClientUpdateEvent clientUpdateEvent){
        kafkaProducer.updateClient(clientUpdateEvent.client());
    }
}
