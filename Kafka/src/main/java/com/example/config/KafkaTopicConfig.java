package com.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    //Create the Kafka topic for POST requests
    @Bean
    public NewTopic postTopic(){
        return TopicBuilder.name("postClient")
                .partitions(2)
                .replicas(2)
                .build();
    }

    //Create the Kafka topic for PUT requests
    @Bean
    public NewTopic updateTopic(){
        return TopicBuilder.name("updateClient")
                .partitions(2)
                .replicas(2)
                .build();
    }

    //Create the Kafka topic for Delete requests
    @Bean
    public NewTopic deleteTopic(){
        return TopicBuilder.name("deleteClient")
                .partitions(2)
                .replicas(2)
                .build();
    }
}
