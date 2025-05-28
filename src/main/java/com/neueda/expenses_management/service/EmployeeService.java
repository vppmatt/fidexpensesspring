package com.neueda.expenses_management.service;

import com.neueda.expenses_management.data.EmployeeDao;
import com.neueda.expenses_management.exceptions.EmployeeNotFoundException;
import com.neueda.expenses_management.model.Department;
import com.neueda.expenses_management.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    EmployeePublisher employeePublisher;

    public List<Employee> getEmployees() {
        List<Employee> employees = employeeDao.getAllEmployees();

        employees = employees.stream().map(it -> {
            it.setFirstname(it.getFirstname().toUpperCase());
            return it;
        }).toList();

        return employees;
    }

    public Employee addEmployee(Employee newEmployee) {

        Employee e = employeeDao.save(newEmployee);

        employeePublisher.sendMessage("employee.created", e);
        return e;

    }

    public Employee getEmployee(Integer employeeId) throws EmployeeNotFoundException {
        return employeeDao.getById(employeeId);
    }
}
