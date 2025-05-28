package com.neueda.expenses_management.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neueda.expenses_management.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeePublisher {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendNewEmployeeMessage(Employee employee) {
        ObjectMapper om = new ObjectMapper();
        om.findAndRegisterModules();
        //send (topic, key, value)
        try {
            kafkaTemplate.send("demo1", employee.getId().toString(), om.writeValueAsString(employee));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
