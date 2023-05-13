package com.example.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "test-topic", groupId = "groupId")
    void listener(String data){
        System.out.println("Listener received: " + data);
    }
    //todo: figure out how to react when receiving data from the topic
}
