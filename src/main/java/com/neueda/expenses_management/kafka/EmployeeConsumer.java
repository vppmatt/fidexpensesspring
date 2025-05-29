package com.neueda.expenses_management.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neueda.expenses_management.data.EmployeeDao;
import com.neueda.expenses_management.model.Employee;
import com.neueda.expenses_management.model.EmployeeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class EmployeeConsumer {

    @Value("${creator}")
    private String creator;

    @Autowired
    EmployeeDao employeeDao;

    @KafkaListener(topics = "employees", groupId="${creator}")
    public void receiveNoticeOfNewEmployee(
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_TIMESTAMP)String timestamp,
            String value) {

        System.out.println("Message received from kafka" + value);

        //convert value to employeeMessage
        ObjectMapper om = new ObjectMapper();
        om.findAndRegisterModules();

        try {
            EmployeeMessage em = om.readValue(value, EmployeeMessage.class);
            //check if creator is someone different
            if(!em.getCreator().equals(creator)) {
                Employee e = em.asEmployee();
                e.setId(null);
                employeeDao.save(e);
            }


        } catch (JsonProcessingException e) {
            System.out.println("ERROR IN JSON PROCESSING " + value);
            System.out.println(e);
            throw new RuntimeException(e);
        }



    }


}
