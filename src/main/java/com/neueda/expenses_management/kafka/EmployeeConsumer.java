package com.neueda.expenses_management.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class EmployeeConsumer {

    @KafkaListener(topics = "employees", groupId="matt")
    public void receiveNoticeOfNewEmployee(
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_TIMESTAMP)String timestamp,
            String value) {
        //convert value to employeeMessage
        //check if creator is someone differetn
        //if it is save employee to database
        System.out.println("Message received " + value);
    }


}
