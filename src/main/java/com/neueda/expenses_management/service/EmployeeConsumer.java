package com.neueda.expenses_management.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class EmployeeConsumer {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeConsumer.class);

    @KafkaListener(topics = "employees", groupId="employee-group1")
    public void loggingExample(
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_TIMESTAMP) String timestamp,
            String value){
        logger.info(String.format("********** EmployeeConsumer consumed message: key %s, topic %s, timestamp %s, value %s", key, topic, timestamp, value));
    }

    @KafkaListener(topics = "employees", groupId="employee-group2")
    public void actionExample(
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_TIMESTAMP) String timestamp,
            String value) {
        logger.info(String.format("********** EmployeeConsumer actioned message: key %s, topic %s, timestamp %s, value %s", key, topic, timestamp, value));
    }

}
