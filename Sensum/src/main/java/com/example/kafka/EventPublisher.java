package com.example.kafka;

import com.example.Client;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public EventPublisher(ApplicationEventPublisher eventPublisher) {

        this.eventPublisher = eventPublisher;
    }

    public void publishClientRegisteredEvent(Client client) {
        ClientRegisteredEvent event = new ClientRegisteredEvent(client);
        eventPublisher.publishEvent(event);
    }
}

