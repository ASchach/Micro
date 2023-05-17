package com.example.kafka;

import com.example.Client;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private final KafkaTemplate<String, Client> kafkaTemplate;

    private final KafkaTemplate<String, Integer> kafkaTemplate2;

    //Constructor
    public KafkaProducer(KafkaTemplate<String, Client> kafkaTemplate, KafkaTemplate<String, Integer> kafkaTemplate2) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTemplate2 = kafkaTemplate2;
    }

    //Kafka producer for "postClient" topic
    public void postClient(Client client){
        kafkaTemplate.send("postClient", client);
    }

    //Kafka producer for "deleteClient" topic
    public void deleteClient(int id){
        kafkaTemplate2.send("deleteClient", id);
    }

    //Kafka producer for "updateClient" topic
    public void updateClient(Client client) {kafkaTemplate.send("updateClient", client);}
}
