package com.neueda.expenses_management.model;

//This is the version of the employee we will send to and receive from Kafka
public class EmployeeMessage extends Employee {

    public String getCreator() {
        return "matt";
    }

    public EmployeeMessage(Employee e) {
        super(e.getId(), e.getFirstname(), e.getLastname(), e.getDateJoined(), e.getDepartment(), e.getCostCenter());
    }

    public Employee asEmployee() {
        return this;
    }
}
