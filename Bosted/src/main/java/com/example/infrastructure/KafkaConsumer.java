package com.example.infrastructure;

import com.example.domain.Client;
import com.example.domain.ClientService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class KafkaConsumer {

    ClientService clientService;

    public KafkaConsumer(ClientService clientService) {
        this.clientService = clientService;
    }


    //Kafka consumer for "postClient" topic
    @KafkaListener(topics = "postClient", groupId = "bosted")
    private void listenPost(Client client){
        System.out.println("listener@Bosted received: " + client + " On topic: postClient");
        clientService.registerClient(client);
    }

    //Kafka consumer for "deleteClient" topic
    @KafkaListener(topics = "deleteClient", groupId = "bosted")
    private void listenDelete(BigInteger cpr){
        System.out.println("Listener@Bosted received: " + cpr + " On topic: deleteClient" );
        clientService.deleteClient(cpr);
    }

    //Kafka consumer for "updateClient" topic
    @KafkaListener(topics = "updateClient", groupId = "bosted")
    private void listenUpdate(Client client){
        System.out.println("Listener@Bosted received: " + client + " On topic: updateClient");
        clientService.updateClient(client);
    }
}

