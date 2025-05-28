package com.neueda.expenses_management.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neueda.expenses_management.model.Employee;
import com.neueda.expenses_management.model.EmployeeMessage;
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

        EmployeeMessage em = new EmployeeMessage(employee);
        //send (topic, key, value)
        try {
            kafkaTemplate.send("employees", employee.getId().toString(), om.writeValueAsString(em));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
