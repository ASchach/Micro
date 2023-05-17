package com.example.kafka;

import com.example.Client;
import com.example.ClientRegistrationRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public EventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    //ClientRegistration event
    public void publishClientRegisteredEvent(ClientRegistrationRequest clientRegistrationRequest) {
        ClientRegistrationEvent event = new ClientRegistrationEvent(
                Client.builder()
                        .cpr(clientRegistrationRequest.cpr())
                        .firstName(clientRegistrationRequest.firstName())
                        .lastName(clientRegistrationRequest.lastName())
                        .address(clientRegistrationRequest.address())
                        .birthYear(clientRegistrationRequest.birthYear())
                        .build());
        System.out.println("Client registration request event published");
        eventPublisher.publishEvent(event);
    }

    //ClientDeletion event
    public void publishClientDeletionEvent(int id){
        ClientDeletionEvent event = new ClientDeletionEvent(id);
        eventPublisher.publishEvent(event);
    }
}

