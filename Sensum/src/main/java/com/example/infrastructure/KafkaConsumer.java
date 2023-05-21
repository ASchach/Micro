package com.example.infrastructure;

import com.example.domain.Client;
import com.example.domain.ClientService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaConsumer {

    ClientService clientService;

    public KafkaConsumer(ClientService clientService) {
        this.clientService = clientService;
    }


    //Kafka consumer for "postClient" topic
    @KafkaListener(topics = "postClient", groupId = "sensum")
    private void listenPost(Client client){
        System.out.println("listener@Sensum received: " + client + " On topic: postClient ");
        clientService.registerClient(client);
    }

    //Kafka consumer for "deleteClient" topic
    @KafkaListener(topics = "deleteClient", groupId = "sensum")
    private void listenDelete(int id){
        System.out.println("Listener@Sensum received: " + id + " On topic: deleteClient ");
        clientService.deleteClient(id);
    }

    //Kafka consumer for "updateClient" topic
    @KafkaListener(topics = "updateClient", groupId = "sensum")
    private void listenUpdate(Client client){
        System.out.println("Listener@Sensum received: " + client + " On topic updateClient ");
        clientService.updateClient(client);
    }
}

