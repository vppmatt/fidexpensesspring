package com.neueda.expenses_management.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;



@Service
public class EmployeePublisher {

    private static final Logger logger = LoggerFactory.getLogger(EmployeePublisher.class);
    private static final String TOPIC_NAME = "employees";

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String key, String value){
        logger.info(String.format("********** MyPublisher is sending message: %s:%s", key, value));

        this.kafkaTemplate.send(TOPIC_NAME, key, value);
    }
}
