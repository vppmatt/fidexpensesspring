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

    public EmployeeMessage() {
        super();
    }

    public EmployeeMessage(Employee e, String creator) {
        super(e.getId(), e.getFirstname(), e.getLastname(), e.getDateJoined(), e.getDepartment(), e.getCostCenter());
        this.creator=creator;
    }

    public Employee asEmployee() {
        return this;
    }

    @Override
    public String toString() {
        return "EmployeeMessage{" +
                "creator='" + creator + '\'' +
                "} " + super.toString();
    }
}
