package com.example.domain.event;

import com.example.infrastructure.KafkaProducer;
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
        kafkaProducer.postClient(clientRegistrationEvent.client());
    }

    //ClientDeletionEvent
    @EventListener
    public void handleDeletionEvent(ClientDeletionEvent clientDeletionEvent){
        kafkaProducer.deleteClient(clientDeletionEvent.cpr);
    }

    //ClientUpdateEvent
    @EventListener
    public void handleUpdateEvent(ClientUpdateEvent clientUpdateEvent){
        kafkaProducer.updateClient(clientUpdateEvent.client());
    }
}
