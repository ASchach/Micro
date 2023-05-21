package com.example.infrastructure;

import com.example.domain.Client;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class KafkaProducer {

    private final KafkaTemplate<String, Client> kafkaTemplate;

    private final KafkaTemplate<Object, BigInteger> kafkaTemplate2;

    //Constructor
    public KafkaProducer(KafkaTemplate<String, Client> kafkaTemplate, KafkaTemplate<Object, BigInteger> kafkaTemplate2) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTemplate2 = kafkaTemplate2;
    }

    //Kafka producer for "postClient" topic
    public void postClient(Client client){
        kafkaTemplate.send("postClient", client);
    }

    //Kafka producer for "deleteClient" topic
    public void deleteClient(BigInteger cpr){
        kafkaTemplate2.send("deleteClient", cpr);
    }

    //Kafka producer for "updateClient" topic
    public void updateClient(Client client) {kafkaTemplate.send("updateClient", client);}
}
