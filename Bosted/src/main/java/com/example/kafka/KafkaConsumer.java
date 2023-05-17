package com.example.kafka;

import com.example.Client;
import com.example.ClientService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private final ClientService clientService;

    public KafkaConsumer(ClientService clientService) {
        this.clientService = clientService;
    }


    //Kafka consumer for "postClient" topic
    @KafkaListener(topics = "postClient", groupId = "bosted")
    private void listenPost(Client client){
        System.out.println("listener received: " + client);
        clientService.registerClient(client);
    }

    //Kafka consumer for "deleteClient" topic
    @KafkaListener(topics = "deleteClient", groupId = "bosted")
    private void listenDelete(int id){
        System.out.println("Listener received: " + id);
        clientService.deleteClient(id);
    }
}

