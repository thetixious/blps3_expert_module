package org.tix.lab3_1.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = "test",groupId = "my-group")
    public void receiveMessage(String message){
        System.out.println(message);
    }
}
