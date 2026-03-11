package com.example.demo.logic;

import com.example.demo.component.StateTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private static final Logger log = LoggerFactory.getLogger(KafkaService.class);

    @KafkaListener(topics = "myTopic", groupId = "group1")
    public void listen(StateTable stateTable) {
        log.info("Messaggio ricevuto: {}", stateTable);
    }

}
