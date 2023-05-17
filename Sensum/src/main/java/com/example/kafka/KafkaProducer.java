package com.example.kafka;

import com.example.Client;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    KafkaTemplate<Integer, Client> kafkaTemplate;

    public void postClient(Client client){
        kafkaTemplate.send("postClient", client);
    }
}
