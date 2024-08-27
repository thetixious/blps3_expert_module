package org.tix.lab3_1.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.tix.lab3_1.model.ExpertMessage;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = "expertAudition",groupId = "my-group", containerFactory = "userKafkaListenerContainerFactory")
    public void receiveMessage(ExpertMessage expertMessage){
        System.out.println(expertMessage.getName());
        System.out.println(expertMessage.getSurname());
    }
}
