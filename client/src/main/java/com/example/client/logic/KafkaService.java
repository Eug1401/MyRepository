package com.example.client.logic;

import com.example.client.component.StateTable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private final KafkaTemplate<String, StateTable> kafkaTemplate;

    private final StateTable stateTable;

    public KafkaService(KafkaTemplate<String, StateTable> kafkaTemplate, StateTable stateTable) {
        this.kafkaTemplate = kafkaTemplate;
        this.stateTable = stateTable;
    }

    public String  sendMessage() {
        kafkaTemplate.send("myTopic", "stateTable", stateTable );

        return "Send Success";
    }
}
