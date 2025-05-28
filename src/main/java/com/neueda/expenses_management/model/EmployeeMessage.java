package com.neueda.expenses_management.model;

//This is the version of the employee we will send to and receive from Kafka
public class EmployeeMessage extends Employee {

    private String creator;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public EmployeeMessage(Employee e) {
        super(e.getId(), e.getFirstname(), e.getLastname(), e.getDateJoined(), e.getDepartment(), e.getCostCenter());
        this.creator = "matt";
    }

    public Employee asEmployee() {
        return this;
    }
}
