package com.example.domain.event;

import com.example.domain.Client;
import com.example.presentation.ClientRegistrationRequest;
import com.example.presentation.ClientUpdateRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Objects;

@Component
public class EventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public EventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    //ClientRegistration-event publisher
    public void publishClientRegisteredEvent(ClientRegistrationRequest clientRegistrationRequest) {
        if (String.valueOf(clientRegistrationRequest.cpr()).length() == 10){
            if (String.valueOf(clientRegistrationRequest.birthYear()).length() == 4){
                if (clientRegistrationRequest.firstName()!= null
                        && clientRegistrationRequest.lastName() != null){
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
                else {System.err.println("First name & last name must not be null");}
            }
            else {System.err.println("Year of birth must contain 4 digits");}
        }
        else {System.err.println("CPR must contain 10 digits");}

    }

    //ClientDeletion-event publisher
    public void publishClientDeletionEvent(BigInteger cpr){
        ClientDeletionEvent event = new ClientDeletionEvent(cpr);
        eventPublisher.publishEvent(event);
    }

    //ClientUpdate-event publisher
    public void publishClientUpdateEvent(ClientUpdateRequest clientUpdateRequest, BigInteger cpr) {
        if (Objects.equals(clientUpdateRequest.cpr(), cpr)){
            if (clientUpdateRequest.firstName()!= null
                    && clientUpdateRequest.lastName() != null){
            ClientUpdateEvent event = new ClientUpdateEvent(
                    Client.builder()
                            .cpr(clientUpdateRequest.cpr())
                            .firstName(clientUpdateRequest.firstName())
                            .lastName(clientUpdateRequest.lastName())
                            .address(clientUpdateRequest.address())
                            .birthYear(clientUpdateRequest.birthYear())
                            .build());
            eventPublisher.publishEvent(event);
            }
            else {System.err.println("First name & last name must not be null");}
        }
        else {System.err.println("Illegal action: change CPR from: " + cpr + "to: " + clientUpdateRequest.cpr());}
    }
}

