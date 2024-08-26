package org.tix.lab3_1.service;

import ch.qos.logback.core.encoder.JsonEscapeUtil;
import org.springframework.stereotype.Service;

@Service
public class ApprovingService {
    private final KafkaConsumerService kafkaConsumerService;

    public ApprovingService(KafkaConsumerService kafkaConsumerService) {
        this.kafkaConsumerService = kafkaConsumerService;
    }

}
