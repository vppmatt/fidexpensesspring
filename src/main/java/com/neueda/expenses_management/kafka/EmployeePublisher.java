package com.neueda.expenses_management.kafka;

import com.neueda.expenses_management.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeePublisher {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendNewEmployeeMessage(Employee employee) {
        //send (topic, key, value)
        kafkaTemplate.send("demo1", employee.getId().toString(), employee.toString());

    }

}
