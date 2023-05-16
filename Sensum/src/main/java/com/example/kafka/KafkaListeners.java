package com.example.kafka;

import com.example.ClientController;
import com.example.ClientRegistrationRequest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    private final ClientController clientController;

    @Autowired
    public KafkaListeners(ClientController clientController) {
        this.clientController = clientController;
    }

//    @KafkaListener(topics = "test-topic", groupId = "groupId")
//    void listener(String data){
//        System.out.println("Listener received: " + data);
//    }
//    //todo: figure out how to react when receiving data from the topic
//
//    @KafkaListener(topics = "postClient", groupId = "sensum")
//    void listener(ConsumerRecord<?, ?> record) {
//        ClientRegistrationRequest clientRegistrationRequest = (ClientRegistrationRequest) record.value();
//        System.out.println("Listener received: " + clientRegistrationRequest);
//        clientController.registerClient(clientRegistrationRequest);
//    }

}
