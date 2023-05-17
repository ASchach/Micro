package com.example.domain.event;

import com.example.domain.Client;
import com.example.presentation.ClientRegistrationRequest;
import com.example.presentation.ClientUpdateRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public EventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    //ClientRegistration-event publisher
    public void publishClientRegisteredEvent(ClientRegistrationRequest clientRegistrationRequest) {
        ClientRegistrationEvent event = new ClientRegistrationEvent(
                Client.builder()
                        .cpr(clientRegistrationRequest.cpr())
                        .firstName(clientRegistrationRequest.firstName())
                        .lastName(clientRegistrationRequest.lastName())
                        .address(clientRegistrationRequest.address())
                        .birthYear(clientRegistrationRequest.birthYear())
                        .build());
        eventPublisher.publishEvent(event);
    }

    //ClientDeletion-event publisher
    public void publishClientDeletionEvent(int id){
        ClientDeletionEvent event = new ClientDeletionEvent(id);
        eventPublisher.publishEvent(event);
    }

    //ClientUpdate-event publisher
    public void publishClientUpdateEvent(ClientUpdateRequest clientUpdateRequest) {
        ClientUpdateEvent event = new ClientUpdateEvent(
                Client.builder()
                        .id(clientUpdateRequest.id())
                        .cpr(clientUpdateRequest.cpr())
                        .firstName(clientUpdateRequest.firstName())
                        .lastName(clientUpdateRequest.lastName())
                        .address(clientUpdateRequest.address())
                        .birthYear(clientUpdateRequest.birthYear())
                        .build());
        eventPublisher.publishEvent(event);
    }
}

